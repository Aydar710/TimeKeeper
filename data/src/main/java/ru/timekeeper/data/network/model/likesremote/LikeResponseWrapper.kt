package ru.timekeeper.data.network.model.likesremote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LikeResponseWrapper (

    @SerializedName("response")
    @Expose
    var response: LikeResponse? = null

)
