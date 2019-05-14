package ru.timekeeper.viewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.timekeeper.SharedPrefWrapper
import ru.timekeeper.data.network.model.groupWallRemote.Item
import ru.timekeeper.data.repository.VkRepository
import javax.inject.Inject

class VkGroupWallViewModel @Inject constructor(
    private val repository: VkRepository,
    private val sPref: SharedPrefWrapper
) : ViewModel() {

    val posts: MutableLiveData<List<Item>> = MutableLiveData()

    fun loadGroupWall(groupId: String) {
        repository.getGroupPosts(groupId = groupId, token = sPref.getTokenFromPreferences())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                posts.value = it
            }, {
                it.printStackTrace()
            })
    }

    fun loadNextPosts(groupId: String, currentPage: Int, pagSize: Int) {
        repository.getGroupPosts(groupId, token = sPref.getTokenFromPreferences(),
            currentPage = currentPage, pagSize = pagSize)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val postList : MutableList<Item> = posts.value as MutableList<Item>
                postList.addAll(it)
                posts.value = postList

            },{
                it.printStackTrace()
            })
    }
}
