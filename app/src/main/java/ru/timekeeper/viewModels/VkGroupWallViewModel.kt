package ru.timekeeper.viewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ru.timekeeper.data.network.model.groupWallRemote.Item
import ru.timekeeper.data.repository.SharedPrefWrapper
import ru.timekeeper.data.repository.VkRepository
import javax.inject.Inject

class VkGroupWallViewModel @Inject constructor(
        private val repository: VkRepository,
        private val sPref: SharedPrefWrapper
) : ViewModel() {

    val posts: MutableLiveData<List<Item>> = MutableLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()

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
                .subscribe({
                    posts.value = it
                }, {
                    it.printStackTrace()
                })

        compositeDisposable.add(disposable)
    }

    fun loadNextPosts(groupId: String, currentPage: Int, pagSize: Int) {
        val disposable = repository.getGroupPosts(groupId, token = sPref.getToken(),
                currentPage = currentPage, pagSize = pagSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val postList: MutableList<Item> = posts.value as MutableList<Item>
                    postList.addAll(it)
                    posts.value = postList

                }, {
                    it.printStackTrace()
                })

        compositeDisposable.add(disposable)
    }

    fun addLike(postId: Int, postType: String, groupId: String) {
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

    fun repost(groupId: Int, postId: Int) {
        repository.repost("-$groupId", "$postId", sPref.getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({repostCount ->
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

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
