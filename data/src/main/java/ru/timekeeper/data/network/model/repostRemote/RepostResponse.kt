package ru.timekeeper.data.network.model.repostRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RepostResponse (

    @SerializedName("success")
    @Expose
    var success: Int? = null,
    @SerializedName("post_id")
    @Expose
    var postId: Int? = null,
    @SerializedName("reposts_count")
    @Expose
    var repostsCount: Int? = null,
    @SerializedName("likes_count")
    @Expose
    var likesCount: Int? = null

)
