package ru.timekeeper.data.network.model.groupsRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Response (

    @SerializedName("count")
    @Expose
    var count: Int? = null,
    @SerializedName("items")
    @Expose
    var items: List<Group>? = null

)
