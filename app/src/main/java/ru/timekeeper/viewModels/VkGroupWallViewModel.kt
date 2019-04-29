package ru.timekeeper.viewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.timekeeper.data.network.model.groupWallRemote.Item
import ru.timekeeper.data.repository.VkRepository
import javax.inject.Inject

class VkGroupWallViewModel @Inject constructor(private val repository: VkRepository) : ViewModel() {

    private var posts: Single<List<Item>> = loadGroupWall("78eaa615bbd903ba1104fed89b6a28a5a4a01f026c7a53f61df18d0ec6d8c2a1fc7aaea05db1e1d0106f0")

    var postsLiveData: LiveData<List<Item>> = LiveDataReactiveStreams.fromPublisher(posts.toFlowable())

    fun loadGroupWall(token: String): Single<List<Item>> =
            repository.getGroupPosts("-41696672", token = token)
                    .observeOn(AndroidSchedulers.mainThread())
}
