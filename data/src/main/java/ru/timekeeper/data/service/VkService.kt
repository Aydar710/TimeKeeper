package ru.timekeeper.data.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.timekeeper.data.network.model.groupByIdRemote.GroupByIdResponseWrapper
import ru.timekeeper.data.network.model.groupWallRemote.GroupWallResponseWrapper
import ru.timekeeper.data.network.model.groupsRemote.GroupsResponseWrapper
import ru.timekeeper.data.network.model.likesremote.LikeResponseWrapper
import ru.timekeeper.data.network.model.tokenstate.TokenStateResponseWrapper

interface VkService {

    @GET("wall.get")
    fun getGroupPosts(
        @Query("owner_id") ownerId: String,
        @Query("count") count: String,
        @Query("offset") offset: String = "0",
        @Query("access_token") access_token: String
    ): Single<GroupWallResponseWrapper>


    @GET("groups.search")
    fun getGroups(
        @Query("q") queryText: String?,
        @Query("access_token") access_token: String
//            @Query("type") type : String = "group"
    ): Single<GroupsResponseWrapper>

    @GET("groups.get")
    fun getUsersGroups(
        @Query("user_id") userId: String,
        @Query("extended") extended: String = "1",
        @Query("count") count: String = "15",
        @Query("access_token") token: String
    ): Single<GroupsResponseWrapper>

    @GET("groups.getById")
    fun getGroupsById(
        @Query("group_ids") groupIds: String,
        @Query("access_token") token: String
    ): Single<GroupByIdResponseWrapper>

    @GET("secure.checkToken")
    fun checkIfTokenIsValid(
        @Query("token") token: String,
        @Query("ip") ip: String,
        @Query("access_token") accessToken: String
    ): Single<TokenStateResponseWrapper>

    @GET("likes.add")
    fun addLike(
        @Query("type") type: String,
        @Query("item_id") itemId: String,
        @Query("access_token") accessToken: String,
        @Query("owner_id") groupId: String
    ): Single<LikeResponseWrapper>

}
