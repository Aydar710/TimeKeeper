package ru.timekeeper.data.network.model.groupWallRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Response (

        @SerializedName("count")
    @Expose
    var count: Int? = null,
        @SerializedName("items")
    @Expose
    var items: List<Item>? = null,
        @SerializedName("profiles")
    @Expose
    var profiles: List<Any>? = null,
        @SerializedName("groups")
    @Expose
    var groups: List<Group>? = null

)
