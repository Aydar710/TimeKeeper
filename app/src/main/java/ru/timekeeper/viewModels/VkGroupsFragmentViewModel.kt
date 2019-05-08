package ru.timekeeper.viewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.timekeeper.SharedPrefWrapper
import ru.timekeeper.data.network.model.groupsRemote.Group
import ru.timekeeper.data.repository.VkRepository
import javax.inject.Inject

class VkGroupsFragmentViewModel @Inject constructor(
    private val repository: VkRepository,
    private val sPref: SharedPrefWrapper
) : ViewModel() {

    var groups: MutableLiveData<List<Group>> = MutableLiveData()


    fun getUserGroups(userId: String) {
        repository.getUsersGroups(userId = userId, token = sPref.getTokenFromPreferences())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                groups.value = it
            }, {
                it.printStackTrace()
            })
    }
}
