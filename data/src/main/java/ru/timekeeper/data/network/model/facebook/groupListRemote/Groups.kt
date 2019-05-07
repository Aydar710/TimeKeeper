package ru.timekeeper.data.network.model.facebook.groupListRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Groups(

    @SerializedName("data")
    @Expose
    var data: List<Datum>? = null,
    @SerializedName("paging")
    @Expose
    var paging: Paging? = null
)
