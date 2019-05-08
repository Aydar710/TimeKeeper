package ru.timekeeper.data.network.model.facebook.grouplistremote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(

        @SerializedName("height")
        @Expose
        var height: Int? = null,
        @SerializedName("is_silhouette")
        @Expose
        var isSilhouette: Boolean? = null,
        @SerializedName("url")
        @Expose
        var url: String? = null,
        @SerializedName("width")
        @Expose
        var width: Int? = null

)
