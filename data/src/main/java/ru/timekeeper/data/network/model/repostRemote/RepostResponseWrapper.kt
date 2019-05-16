package ru.timekeeper.data.network.model.repostRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RepostResponseWrapper(

        @SerializedName("response")
        @Expose
        var response: RepostResponse? = null

)
