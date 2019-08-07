package ru.timekeeper.viewModels

import android.arch.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.timekeeper.data.network.model.groupWallRemote.Item
import ru.timekeeper.data.repository.SharedPrefWrapper
import ru.timekeeper.data.repository.VkRepository
import javax.inject.Inject

class VkGroupWallViewModel @Inject constructor(
    private val repository: VkRepository,
    private val sPref: SharedPrefWrapper
) : BaseViewModel() {

    val posts: MutableLiveData<List<Item>> = MutableLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun loadGroupWall(groupId: String) {
        val disposable = repository.getGroupPosts(groupId = groupId, token = sPref.getToken())
            .doOnSubscribe {
                isLoading.postValue(true)
            }
            .doOnSuccess {
                isLoading.postValue(false)
            }
            .doOnError {
                isLoading.postValue(false)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .map { itemList ->
                getPopularPosts(itemList)
            }
            .subscribe({
                posts.value = it
            }, {
                it.printStackTrace()
            })

        compositeDisposable.add(disposable)
    }

    fun loadNextPosts(groupId: String, currentPage: Int, pagSize: Int) {
        val disposable = repository.getGroupPosts(
            groupId, token = sPref.getToken(),
            currentPage = currentPage, pagSize = pagSize
        )
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                getPopularPosts(it)
            }
            .subscribe({
                val postList: MutableList<Item> = posts.value as MutableList<Item>
                postList.addAll(it)
                posts.value = postList

            }, {
                it.printStackTrace()
            })

        compositeDisposable.add(disposable)
    }

    fun onLikeClicked(postId: Int, postType: String, groupId: String, isPostLiked: Boolean) {
        if (!isPostLiked)
            addLike(postId, postType, groupId)
        else
            deleteLike(postId, postType, groupId)
    }

    fun repost(groupId: Int, postId: Int) {
        repository.repost("-$groupId", "$postId", sPref.getToken())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ repostCount ->
                val postList: MutableList<Item> = posts.value as MutableList<Item>
                postList.forEach { item ->
                    if (item.id == postId) {
                        item.reposts?.userReposted = 1
                        item.reposts?.count = repostCount
                    }
                }
                posts.postValue(postList)
            }, {
                it.printStackTrace()
            })
    }

    private fun addLike(postId: Int, postType: String, groupId: String) {
        repository.addLike(postType, postId.toString(), sPref.getToken(), groupId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ likeCount ->
                val postList: MutableList<Item> = posts.value as MutableList<Item>
                postList.forEach { item ->
                    if (item.id == postId) {
                        item.likes?.userLikes = 1
                        item.likes?.count = likeCount
                    }
                }
                posts.postValue(postList)
            }, {
                it.printStackTrace()
            })
    }

    private fun deleteLike(postId: Int, postType: String, groupId: String) {
        repository.deleteLike(postType, postId.toString(), sPref.getToken(), groupId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ likeCount ->
                val postList: MutableList<Item> = posts.value as MutableList<Item>
                postList.forEach { item ->
                    if (item.id == postId) {
                        item.likes?.userLikes = 0
                        item.likes?.count = likeCount
                    }
                }
                posts.postValue(postList)
            }, {
                it.printStackTrace()
            })
    }

    private fun getPopularPosts(list: List<Item>): List<Item> {
        var postList: MutableList<Item> = list as MutableList<Item>
        var itemForRemove: MutableList<Item> = mutableListOf()
        postList.forEach { item ->
            val likesCount = item.likes?.count
            val repostsCount = item.reposts?.count
            val viewsCount = item.views?.count
            val commentsCount = item.comments?.count
            val percent: Double? = repostsCount?.let { repCount ->
                viewsCount?.let { views ->
                    commentsCount?.let { commentCount ->
                        likesCount?.plus(repCount)?.plus(commentCount)?.toDouble()?.div((views.toDouble()))
                    }
                }
            }

            percent?.let {
                val pr = sPref.getPercent()
                val pre = it * 100
                if (pre < pr)
                    itemForRemove.add(item)
            }
        }
        postList.removeAll(itemForRemove)
        return postList
    }
}
