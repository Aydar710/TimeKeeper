package ru.timekeeper.data.network.model.groupWallRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.timekeeper.data.network.model.groupsRemote.GroupsResponse

data class GroupWallResponseWrapper (

    @SerializedName("response")
    @Expose
    val response: GroupsResponse? = null

)
