package ru.timekeeper.data.network.model.facebook.groupListRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Datum (

    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("icon")
    @Expose
    var icon: String? = null,
    @SerializedName("id")
    @Expose
    var id: String? = null
)
