package ru.timekeeper.data.network.model.userinforemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserInfoResponseWrapper(

        @SerializedName("response")
        @Expose
        val response: List<UserInfoResponse>? = null

)
