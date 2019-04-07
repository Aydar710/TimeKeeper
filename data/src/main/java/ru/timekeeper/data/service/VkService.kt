package ru.timekeeper.data.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.timekeeper.data.network.model.groupWallRemote.GroupWallResponse
import ru.timekeeper.data.network.model.groupsRemote.GroupsResponse

interface VkService {

    @GET("wall.get")
    fun getGroupPosts(
        @Query("owner_id") ownerId: String,
        @Query("count") count: String,
        @Query("access_token") access_token : String
    ): Single<GroupWallResponse>


    @GET("groups.search")
    fun getGroups(
        @Query("q") queryText : String?,
        @Query("access_token") access_token : String
//            @Query("type") type : String = "group"
    ) : Single<GroupsResponse>

    @GET("groups.get")
    fun getUsersGroups(
        @Query("user_id") userId : String,
        @Query("extended") extended : String = "1",
        @Query("count") count : String = "30",
        @Query("access_token") token : String
    ) : Single<GroupsResponse>
}