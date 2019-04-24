package ru.timekeeper.viewModels

import android.arch.lifecycle.ViewModel
import ru.timekeeper.data.repository.VkRepository
import javax.inject.Inject

class UserGroupsFragmentViewModel @Inject constructor(val repository: VkRepository) : ViewModel() {

    /*private lateinit var groups: Single<List<Group>>

    var groupsLiveData = LiveDataReactiveStreams.fromPublisher(groups.toFlowable())

    fun getUserGroups(userId: String, token: String) {
        groups = repository.getUsersGroups(userId, count = "30", token = token)
    }*/
}