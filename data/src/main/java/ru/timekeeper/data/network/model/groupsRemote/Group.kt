package ru.timekeeper.data.network.model.groupsRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Group (

    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("screen_name")
    @Expose
    var screenName: String? = null,
    @SerializedName("is_closed")
    @Expose
    var isClosed: Int? = null,
    @SerializedName("type")
    @Expose
    var type: String? = null,
    @SerializedName("is_admin")
    @Expose
    var isAdmin: Int? = null,
    @SerializedName("is_member")
    @Expose
    var isMember: Int? = null,
    @SerializedName("is_advertiser")
    @Expose
    var isAdvertiser: Int? = null,
    @SerializedName("photo_50")
    @Expose
    var photo50: String? = null,
    @SerializedName("photo_100")
    @Expose
    var photo100: String? = null,
    @SerializedName("photo_200")
    @Expose
    var photo200: String? = null

)
