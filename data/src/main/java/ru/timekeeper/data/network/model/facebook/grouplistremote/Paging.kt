package ru.timekeeper.data.network.model.facebook.grouplistremote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Paging(

        @SerializedName("cursors")
        @Expose
        var cursors: Cursors? = null

)
