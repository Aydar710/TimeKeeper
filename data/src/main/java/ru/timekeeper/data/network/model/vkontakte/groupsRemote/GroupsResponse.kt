package ru.timekeeper.data.network.model.vkontakte.groupsRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GroupsResponse (

    @SerializedName("count")
    @Expose
    val count: Int? = null,
    @SerializedName("items")
    @Expose
    val items: List<Group>? = null
)
