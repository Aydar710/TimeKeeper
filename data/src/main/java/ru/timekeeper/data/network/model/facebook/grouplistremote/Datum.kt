package ru.timekeeper.data.network.model.facebook.grouplistremote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Datum(

        @SerializedName("name")
        @Expose
        var name: String? = null,
        @SerializedName("picture")
        @Expose
        var picture: Picture? = null,
        @SerializedName("id")
        @Expose
        var id: String? = null

)
