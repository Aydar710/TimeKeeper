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


    fun getGroupPosts(
        groupId: String, count: String = "10", token: String,
        currentPage: Int = 0, pagSize: Int = 10
    ): Single<List<Item>> {
        var group: Group? = null
        getGroupById(groupId, token)
            .subscribe({
                group = it
            }, {
                it.printStackTrace()
            })

        return vkService.getGroupPosts(groupId, count, access_token = token, offset = "${currentPage * pagSize}")
            .subscribeOn(Schedulers.io())
            .map {
                it.response?.items
            }
            .map { items ->
                items?.let { listItems ->
                    listItems.forEach {
                        it.groupName = group?.name
                        it.groupPhoto = group?.photo100
                        it.groupId = group?.id?.toString()
                    }
                }
                items
            }
    }

    private fun getGroupById(groupId: String, token: String): Single<Group> =
        vkService.getGroupsById(groupId.substring(1), token)
            .subscribeOn(Schedulers.io())
            .map {
                it.groups?.get(0)
            }

    fun getGroupsById(groupIds: String, token: String): Single<List<Group>> =
        vkService.getGroupsById(groupIds, token)
            .subscribeOn(Schedulers.io())
            .map {
                it.groups
            }

    fun getCombinedFeed(groupIds: MutableList<String>, token: String): Observable<List<Item>> {
        for (i in 0 until groupIds.size) {
            groupIds[i] = "-${groupIds[i]}"
        }
        var mergedObservables: MutableList<Observable<List<Item>>> = mutableListOf()
        for (i in 0 until groupIds.size) {
            mergedObservables.add(
                getGroupPosts(groupIds[i], token = token, count = "1").toObservable()
            )
        }
        return Observable.merge(mergedObservables)
    }

    fun addLike(type: String, itemId: String, token: String, groupId : String) =
        vkService.addLike(type, itemId, token, groupId)
            .subscribeOn(Schedulers.io())
            .map {
                it.response?.likes
            }
}
