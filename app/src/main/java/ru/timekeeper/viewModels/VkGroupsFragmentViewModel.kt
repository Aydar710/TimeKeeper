package ru.timekeeper.viewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.timekeeper.App
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

    fun addFavoriteGroup(groupId: Int) {
        val id = HashMap<String, Int>()
        id["id"] = groupId
        App.idsCollection?.add(id)
        markGroupAsFavorite(groupId)
    }

    private fun markGroupAsFavorite(groupId: Int) {
        var groupList: MutableList<Group> = groups.value as MutableList<Group>
        val index = findGroupIndexWithRequiredId(groupId)
        groupList[index].isFavorite = true
        groups.value = groupList
    }

    private fun findGroupIndexWithRequiredId(requiredId: Int): Int {
        groups.value?.forEach {
            if (it.id == requiredId)
                return groups.value?.indexOf(it) ?: -1
        }
        return -1
    }

}
