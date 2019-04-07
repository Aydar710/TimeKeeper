package ru.timekeeper.data.network.model.groupWallRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.timekeeper.data.network.model.groupsRemote.Response

data class GroupWallResponse (

    @SerializedName("response")
    @Expose
    var response: Response? = null

)
