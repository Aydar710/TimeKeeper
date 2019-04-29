package ru.timekeeper.viewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.timekeeper.ACCESS_KEY
import ru.timekeeper.GROUP_ID
import ru.timekeeper.data.network.model.groupWallRemote.Item
import ru.timekeeper.data.repository.VkRepository
import javax.inject.Inject

class VkGroupWallViewModel @Inject constructor(private val repository: VkRepository) : ViewModel() {

    private var posts: Single<List<Item>> = loadGroupWall(ACCESS_KEY)

    var postsLiveData: LiveData<List<Item>> =
            LiveDataReactiveStreams.fromPublisher(posts.toFlowable())

    fun loadGroupWall(token: String): Single<List<Item>> =
            repository.getGroupPosts(GROUP_ID, token = token)
                    .observeOn(AndroidSchedulers.mainThread())
}
