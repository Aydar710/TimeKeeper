package ru.timekeeper.data.network.model.groupByIdRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.timekeeper.data.network.model.groupsRemote.Group

data class GroupByIdResponseWrapper(

    @SerializedName("response")
    @Expose
    var groups: List<Group>? = null
)
