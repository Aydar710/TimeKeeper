package ru.timekeeper.viewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.google.firebase.firestore.CollectionReference
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.timekeeper.SharedPrefWrapper
import ru.timekeeper.data.network.model.groupsRemote.Group
import ru.timekeeper.data.repository.VkRepository
import javax.inject.Inject

class VkGroupsViewModel @Inject constructor(
    private val repository: VkRepository,
    private val sPref: SharedPrefWrapper,
    private val idsCollection: CollectionReference
) : ViewModel() {

    var groups: MutableLiveData<List<Group>> = MutableLiveData()

    var favoriteGroupIds = mutableListOf<String>()

    private fun changeGroupsValue(userId: String) {
        repository.getUsersGroups(userId = userId, token = sPref.getTokenFromPreferences())
            .observeOn(AndroidSchedulers.mainThread())
            .map { groups ->
                groups.forEach { group ->
                    if (checkIfFavoriteGroupIdsContains(group.id)) {
                        group.isFavorite = true
                    }
                }
                groups
            }
            .subscribe({
                groups.value = it
            }, {
                it.printStackTrace()
            })
    }

    fun onStarClicked(groupId: Int) {
        val group = groups.value?.get(findGroupIndexWithRequiredId(groupId))
        group?.let {
            if (!it.isFavorite) {
                it.id?.let { it1 -> addGroupIntoFavorites(it1) }
            } else {
                deleteGroupFromFavorites(groupId)
            }
        }
    }

    fun getGroups(userId: String) {
        val ids = mutableListOf<String>()
        idsCollection.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        val data = document.data
                        val id = data["id"]
                        val idStr = id.toString()
                        ids.add(idStr)
                    }
                }
                favoriteGroupIds.addAll(ids)
                changeGroupsValue(userId)
            }
    }

    private fun addGroupIntoFavorites(groupId: Int) {
        val id = HashMap<String, Int>()
        id["id"] = groupId
        idsCollection.document("$groupId").set(id)
        markGroupAsFavorite(groupId)
    }

    private fun deleteGroupFromFavorites(groupId: Int) {
        idsCollection.document("$groupId").delete()
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


    private fun checkIfFavoriteGroupIdsContains(checkingId: Int?): Boolean {
        favoriteGroupIds.forEach {
            if (it.equals(checkingId.toString()))
                return true
        }
        return false
    }
}
