package ru.timekeeper.data.network.model.groupWallRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Attachment (

    @SerializedName("type")
    @Expose
    var type: String? = null,
    @SerializedName("photo")
    @Expose
    var photo: Photo? = null

)
