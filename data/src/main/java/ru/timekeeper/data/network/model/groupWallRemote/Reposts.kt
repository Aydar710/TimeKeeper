package ru.timekeeper.data.network.model.groupWallRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Reposts (

    @SerializedName("count")
    @Expose
    val count: Int? = null,
    @SerializedName("user_reposted")
    @Expose
    val userReposted: Int? = null

)
