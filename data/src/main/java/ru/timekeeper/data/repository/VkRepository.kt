package ru.timekeeper.data.repository

import com.google.firebase.firestore.CollectionReference
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.timekeeper.data.network.model.groupWallRemote.Item
import ru.timekeeper.data.network.model.groupsRemote.Group
import ru.timekeeper.data.service.VkService

class VkRepository(
    private val vkService: VkService,
    private val idsCollection: CollectionReference
) {

    fun getUsersGroups(userId: String, count: String = "13", token: String): Single<List<Group>> =
        vkService.getUsersGroups(userId, count, token = token)
            .subscribeOn(Schedulers.io())
            .map {
                it.response?.items
            }


    fun getGroupPosts(groupId: String, count: String = "20", token: String): Single<List<Item>> {
        var group: Group? = null
        getGroupById(groupId, token)
            .subscribe({
                group = it
            }, {
                it.printStackTrace()
            })
        return vkService.getGroupPosts(groupId, count, token)
            .subscribeOn(Schedulers.io())
            .map {
                it.response
            }
            .map {
                it?.items
            }
            .map { items ->
                items?.forEach {
                    it.groupName = group?.name
                    it.groupPhoto = group?.photo100
                }
                items
            }
    }

    private fun getGroupById(groupId: String, token: String): Single<Group> =
        vkService.getGroupById(groupId.substring(1), token)
            .subscribeOn(Schedulers.io())
            .map {
                it.groups?.get(0)
            }


    fun getCombinedFeed(groupIds: MutableList<String>, token: String): Observable<List<Item>> {
        for (i in 0 until groupIds.size) {
            groupIds[i] = "-${groupIds[i]}"
        }
        var mergingObservables: MutableList<Observable<List<Item>>> = mutableListOf()
        for (i in 0 until groupIds.size) {
            mergingObservables.add(
                getGroupPosts(groupIds[i], token = token, count = "3").toObservable()
            )
        }
        return Observable.merge(mergingObservables)
    }
}
