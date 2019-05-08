package ru.timekeeper.vkontakte.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.timekeeper.common.SharedPrefWrapper
import ru.timekeeper.data.network.model.vkontakte.groupWallRemote.Item
import ru.timekeeper.data.repository.VkRepository
import javax.inject.Inject

class VkGroupWallViewModel @Inject constructor(private val repository: VkRepository,
                                               private val sPref: SharedPrefWrapper
)
    : ViewModel() {

    val posts: MutableLiveData<List<Item>> = MutableLiveData()

    fun loadGroupWall(token: String, groupId: String) {
        repository.getGroupPosts(groupId = groupId, token = token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    posts.value = it
                },{
                    it.printStackTrace()
                })
    }
}
