package ru.timekeeper.viewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.google.firebase.firestore.CollectionReference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ru.timekeeper.SharedPrefWrapper
import ru.timekeeper.data.network.model.groupsRemote.Group
import ru.timekeeper.data.repository.VkRepository
import javax.inject.Inject

class VkGroupsViewModel @Inject constructor(
    private val repository: VkRepository,
    private val sPref: SharedPrefWrapper,
    private val idsCollection: CollectionReference
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    var groups: MutableLiveData<List<Group>> = MutableLiveData()

    var favoriteGroupIds = mutableListOf<String>()

    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    private fun changeGroupsValue(userId: String) {
        val disposable = repository.getUsersGroups(userId = userId, token = sPref.getToken())
            .doOnSubscribe {
                isLoading.postValue(true)
            }
            .doOnSuccess {
                isLoading.postValue(false)
            }
            .doOnError {
                isLoading.postValue(false)
            }
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
        compositeDisposable.add(disposable)
    }

    fun onStarClicked(groupId: Int) {
        val group = groups.value?.get(findGroupIndexWithRequiredId(groupId))
        group?.let {
            if (!it.isFavorite) {
                it.id?.let { groupId -> addGroupIntoFavorites(groupId) }
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

    fun getFavoriteGroups(userId: String) {
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
                changeFavoriteGroupsValue()
            }
    }

    private fun changeFavoriteGroupsValue() {
        val groupsForQuery: StringBuilder = StringBuilder()
        favoriteGroupIds.forEach {
            groupsForQuery.append("$it,")
        }

        val disposable = repository.getGroupsById(groupsForQuery.toString(), sPref.getToken())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.forEach {
                    it.isFavorite = true
                }
                it
            }
            .subscribe({
                groups.value = it
            }, {
                it.printStackTrace()
            })

        compositeDisposable.add(disposable)
    }

    private fun addGroupIntoFavorites(groupId: Int) {
        val id = HashMap<String, Int>()
        id["id"] = groupId
        idsCollection.document("$groupId").set(id)
        markGroupAsFavorite(groupId)
    }

    private fun deleteGroupFromFavorites(groupId: Int) {
        idsCollection.document("$groupId").delete()
        markGroupAsNotFavorite(groupId)
    }

    private fun markGroupAsFavorite(groupId: Int) {
        val groupList: MutableList<Group> = groups.value as MutableList<Group>
        val index = findGroupIndexWithRequiredId(groupId)
        groupList[index].isFavorite = true
        groups.value = groupList
    }

    private fun markGroupAsNotFavorite(groupId : Int){
        val groupList: MutableList<Group> = groups.value as MutableList<Group>
        val index = findGroupIndexWithRequiredId(groupId)
        groupList[index].isFavorite = false
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

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
