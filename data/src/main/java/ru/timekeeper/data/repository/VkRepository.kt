package ru.timekeeper.data.repository

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.timekeeper.data.network.model.groupWallRemote.Item
import ru.timekeeper.data.network.model.groupsRemote.Group
import ru.timekeeper.data.service.VkService

class VkRepository(private val vkService: VkService) {

    fun getUsersGroups(userId: String, count: String = "13", token: String): Single<List<Group>> =
            vkService.getUsersGroups(userId, count, token = token)
                    .subscribeOn(Schedulers.io())
                    .map {
                        it.response?.items
                    }


    fun getGroupPosts(groupId: String, count: String = "20", token: String): Single<List<Item>> =
            vkService.getGroupPosts(groupId, count, token)
                    .subscribeOn(Schedulers.io())
                    .map {
                        it.response
                    }
                    .map {
                        it?.items
                    }
}
