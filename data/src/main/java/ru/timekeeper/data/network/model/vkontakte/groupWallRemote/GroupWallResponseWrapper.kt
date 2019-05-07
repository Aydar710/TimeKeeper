package ru.timekeeper.data.network.model.vkontakte.groupWallRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GroupWallResponseWrapper (

    @SerializedName("response")
    @Expose
    val response: GroupWallResponse? = null

)
