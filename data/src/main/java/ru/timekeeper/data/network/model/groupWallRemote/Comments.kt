package ru.timekeeper.data.network.model.groupWallRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Comments (

    @SerializedName("count")
    @Expose
    var count: Int? = null,
    @SerializedName("can_post")
    @Expose
    var canPost: Int? = null,
    @SerializedName("groups_can_post")
    @Expose
    var groupsCanPost: Boolean? = null

)
