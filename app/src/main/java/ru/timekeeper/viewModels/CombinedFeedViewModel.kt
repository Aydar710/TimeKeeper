package ru.timekeeper.viewModels

import android.arch.lifecycle.MutableLiveData
import com.google.firebase.firestore.CollectionReference
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.timekeeper.data.network.model.groupWallRemote.Item
import ru.timekeeper.data.repository.SharedPrefWrapper
import ru.timekeeper.data.repository.VkRepository
import javax.inject.Inject

class CombinedFeedViewModel @Inject constructor(
    private val vkRepository: VkRepository,
    private val idsCollection: CollectionReference,
    private val sPref: SharedPrefWrapper
) : BaseViewModel() {

    private var favoriteIds = mutableListOf<String>()

    val posts: MutableLiveData<List<Item>> = MutableLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getCombinedFeed() {
        getFavoriteIds()
    }

    fun onLikeClicked(postId: Int, postType: String, groupId: String, isPostLiked: Boolean) {
        if (!isPostLiked)
            addLike(postId, postType, groupId)
        else
            deleteLike(postId, postType, groupId)
    }

    fun repost(groupId: Int, postId: Int) {
        vkRepository.repost("-$groupId", "$postId", sPref.getToken())
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

    private fun changePostsValue() {
        val disposable = vkRepository.getCombinedFeed(favoriteIds, token = sPref.getToken())
            .doOnSubscribe {
                isLoading.postValue(true)
            }
            .doOnComplete {
                isLoading.postValue(false)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val postsValue: MutableList<Item> = mutableListOf()
                posts.value?.let {
                    postsValue.addAll(it)
                }
                postsValue.addAll(it)
                posts.value = postsValue
            }, {
                it.printStackTrace()
            })

        compositeDisposable.add(disposable)
    }

    private fun getFavoriteIds() {
        idsCollection
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val data = document.data
                    val id = data["id"]
                    favoriteIds.add("$id")
                }
                changePostsValue()
            }
    }

    private fun addLike(postId: Int, postType: String, groupId: String) {
        vkRepository.addLike(postType, postId.toString(), sPref.getToken(), groupId)
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
        vkRepository.deleteLike(postType, postId.toString(), sPref.getToken(), groupId)
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
}
