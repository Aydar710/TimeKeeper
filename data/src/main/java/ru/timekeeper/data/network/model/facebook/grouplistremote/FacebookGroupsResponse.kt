package ru.timekeeper.data.network.model.facebook.grouplistremote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FacebookGroupsResponse(

        @SerializedName("groups")
        @Expose
        var groups: Groups? = null,
        @SerializedName("id")
        @Expose
        var id: String? = null

)
