package ru.timekeeper.data.network.model.groupWallRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Photo (

        @SerializedName("id")
    @Expose
    var id: Int? = null,
        @SerializedName("album_id")
    @Expose
    var albumId: Int? = null,
        @SerializedName("owner_id")
    @Expose
    var ownerId: Int? = null,
        @SerializedName("user_id")
    @Expose
    var userId: Int? = null,
        @SerializedName("sizes")
    @Expose
    var sizes: List<Size>? = null,
        @SerializedName("text")
    @Expose
    var text: String? = null,
        @SerializedName("date")
    @Expose
    var date: Int? = null,
        @SerializedName("post_id")
    @Expose
    var postId: Int? = null,
        @SerializedName("access_key")
    @Expose
    var accessKey: String? = null

)
