package ru.timekeeper.data.network.model.tokenstate

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TokenStateResponse (

    @SerializedName("success")
    @Expose
    var success: Int? = null,
    @SerializedName("user_id")
    @Expose
    var userId: Int? = null,
    @SerializedName("date")
    @Expose
    var date: Int? = null,
    @SerializedName("expire")
    @Expose
    var expire: Int? = null

)
