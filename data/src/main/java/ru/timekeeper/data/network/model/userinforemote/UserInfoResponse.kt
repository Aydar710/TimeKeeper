package ru.timekeeper.data.network.model.userinforemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserInfoResponse(

        @SerializedName("id")
        @Expose
        val id: Int? = null,
        @SerializedName("first_name")
        @Expose
        val firstName: String? = null,
        @SerializedName("last_name")
        @Expose
        val lastName: String? = null,
        @SerializedName("is_closed")
        @Expose
        val isClosed: Boolean? = null,
        @SerializedName("can_access_closed")
        @Expose
        val canAccessClosed: Boolean? = null

)
