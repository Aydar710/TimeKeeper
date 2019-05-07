package ru.timekeeper.data.network.model.facebook.groupListRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Cursors(

    @SerializedName("before")
    @Expose
    var before: String? = null,
    @SerializedName("after")
    @Expose
    var after: String? = null
)
