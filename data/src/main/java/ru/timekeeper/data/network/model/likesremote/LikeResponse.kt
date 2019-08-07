package ru.timekeeper.data.network.model.likesremote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LikeResponse(

    @SerializedName("likes")
    @Expose
    var likes: Int? = null

)
