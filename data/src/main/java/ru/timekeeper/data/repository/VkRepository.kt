package ru.timekeeper.data.repository

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.timekeeper.data.network.model.groupsRemote.Group
import ru.timekeeper.data.service.VkService

class VkRepository(val vkService: VkService) {

    fun getUsersGroups(userId: String, count: String = "30", token: String): Single<List<Group>?> {
        return vkService.getUsersGroups(userId, count, token = token)
            .subscribeOn(Schedulers.io())
            .map { it.response?.items }
    }
}
