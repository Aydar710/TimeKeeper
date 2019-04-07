package ru.timekeeper.data.network.model.groupWallRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Item(

    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("from_id")
    @Expose
    var fromId: Int? = null,
    @SerializedName("owner_id")
    @Expose
    var ownerId: Int? = null,
    @SerializedName("date")
    @Expose
    var date: Int? = null,
    @SerializedName("marked_as_ads")
    @Expose
    var markedAsAds: Int? = null,
    @SerializedName("post_type")
    @Expose
    var postType: String? = null,
    @SerializedName("text")
    @Expose
    var text: String? = null,
    @SerializedName("is_pinned")
    @Expose
    var isPinned: Int? = null,
    @SerializedName("attachments")
    @Expose
    var attachments: List<Attachment>? = null,
    @SerializedName("post_source")
    @Expose
    var postSource: PostSource? = null,
    @SerializedName("comments")
    @Expose
    var comments: Comments? = null,
    @SerializedName("likes")
    @Expose
    var likes: Likes? = null,
    @SerializedName("reposts")
    @Expose
    var reposts: Reposts? = null,
    @SerializedName("views")
    @Expose
    var views: Views? = null,
    @SerializedName("is_favorite")
    @Expose
    var isFavorite: Boolean? = null

)
