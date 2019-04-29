package ru.timekeeper.viewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.timekeeper.ACCESS_KEY
import ru.timekeeper.USER_ID
import ru.timekeeper.data.network.model.groupsRemote.Group
import ru.timekeeper.data.repository.VkRepository
import javax.inject.Inject

class VkUserGroupsFragmentViewModel @Inject constructor(private val repository: VkRepository)
    : ViewModel() {

    private var groups: Single<List<Group>> =
            loadUserGroups(
                    ACCESS_KEY)

    var groupsLiveData: LiveData<List<Group>> = LiveDataReactiveStreams.fromPublisher(groups.toFlowable())

    fun loadUserGroups(token: String) =
            repository.getUsersGroups(USER_ID.toString(), count = "10", token = token)
                    .observeOn(AndroidSchedulers.mainThread())

    fun getUserGroups(token: String) {
        groups = repository.getUsersGroups(USER_ID.toString(), count = "10", token = token)
                .observeOn(AndroidSchedulers.mainThread())
    }
}
