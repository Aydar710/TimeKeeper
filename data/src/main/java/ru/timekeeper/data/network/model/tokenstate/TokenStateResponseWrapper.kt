package ru.timekeeper.data.network.model.tokenstate

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TokenStateResponseWrapper (

    @SerializedName("response")
    @Expose
    var response: TokenStateResponse? = null

)
