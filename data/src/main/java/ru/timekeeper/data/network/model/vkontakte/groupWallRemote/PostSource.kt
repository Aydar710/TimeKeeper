package ru.timekeeper.data.network.model.vkontakte.groupWallRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostSource (

    @SerializedName("type")
    @Expose
    val type: String? = null

)
