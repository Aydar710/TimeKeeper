package ru.timekeeper.viewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.timekeeper.data.network.model.groupsRemote.Group
import ru.timekeeper.data.repository.VkRepository
import javax.inject.Inject

class VkUserGroupsFragmentViewModel @Inject constructor(private val repository: VkRepository)
    : ViewModel() {


    private var groups: Single<List<Group>> =
            loadUserGroups(
                    "78eaa615bbd903ba1104fed89b6a28a5a4a01f026c7a53f61df18d0ec6d8c2a1fc7a")

    var groupsLiveData: LiveData<List<Group>> = LiveDataReactiveStreams.fromPublisher(groups.toFlowable())

    fun loadUserGroups(token: String) =
            repository.getUsersGroups(116812347.toString(), count = "10", token = token)
                    .observeOn(AndroidSchedulers.mainThread())

    fun getUserGroups(token: String) {
        groups = repository.getUsersGroups(116812347.toString(), count = "10", token = token)
                .observeOn(AndroidSchedulers.mainThread())
    }
}
