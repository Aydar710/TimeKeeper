package ru.timekeeper.data.network.model.groupWallRemote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Group {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("screen_name")
    @Expose
    public String screenName;
    @SerializedName("is_closed")
    @Expose
    public Integer isClosed;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("is_admin")
    @Expose
    public Integer isAdmin;
    @SerializedName("is_member")
    @Expose
    public Integer isMember;
    @SerializedName("is_advertiser")
    @Expose
    public Integer isAdvertiser;
    @SerializedName("photo_50")
    @Expose
    public String photo50;
    @SerializedName("photo_100")
    @Expose
    public String photo100;
    @SerializedName("photo_200")
    @Expose
    public String photo200;

}
