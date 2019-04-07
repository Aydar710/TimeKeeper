package ru.timekeeper.data.network.model.groupWallRemote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Likes {

    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("user_likes")
    @Expose
    public Integer userLikes;
    @SerializedName("can_like")
    @Expose
    public Integer canLike;
    @SerializedName("can_publish")
    @Expose
    public Integer canPublish;

}
