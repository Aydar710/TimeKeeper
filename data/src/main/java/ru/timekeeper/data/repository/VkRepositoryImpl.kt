package ru.timekeeper.data.repository

import ru.timekeeper.core.interfaces.VkRepository
import ru.timekeeper.data.service.VkService
import javax.inject.Inject

class VkRepositoryImpl @Inject constructor(val vkService: VkService) : VkRepository {

}
