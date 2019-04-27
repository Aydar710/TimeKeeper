package ru.timekeeper.viewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.timekeeper.data.network.model.groupsRemote.Group
import ru.timekeeper.data.repository.VkRepository
import javax.inject.Inject

class VkUserGroupsFragmentViewModel @Inject constructor(private val repository: VkRepository) : ViewModel() {


    private var groups: Single<List<Group>> = loadUserGroups("02ad39a6772a990c82446fd072fbabc4858fa286fc19c2b7efda5882734a72967f3f3424838e8900b7518")

    var groupsLiveData : LiveData<List<Group>> = LiveDataReactiveStreams.fromPublisher(groups.toFlowable())

    fun loadUserGroups(token: String) =
        repository.getUsersGroups(116812347.toString(), count = "10", token = token)
            .observeOn(AndroidSchedulers.mainThread())

    fun getUserGroups(token: String){
        groups = repository.getUsersGroups(116812347.toString(), count = "10", token = token)
            .observeOn(AndroidSchedulers.mainThread())
    }
}