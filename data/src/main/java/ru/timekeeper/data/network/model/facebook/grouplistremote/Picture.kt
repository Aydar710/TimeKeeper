package ru.timekeeper.data.network.model.facebook.grouplistremote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Picture(

        @SerializedName("data")
        @Expose
        var data: Data? = null

)
