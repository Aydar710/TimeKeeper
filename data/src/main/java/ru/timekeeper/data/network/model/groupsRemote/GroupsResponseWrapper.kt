package ru.timekeeper.data.network.model.groupsRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GroupsResponseWrapper (

    @SerializedName("response")
    @Expose
    val response: GroupsResponse? = null
)
