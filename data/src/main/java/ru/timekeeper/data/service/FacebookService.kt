package ru.timekeeper.data.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.timekeeper.data.network.model.facebook.grouplistremote.FacebookGroupsResponse

interface FacebookService {

    @GET("me")
    fun getFacebookGroups(
            @Query("fields") fileds: String = "groups{name,picture}",
            @Query("access_token") token: String
    ): Single<FacebookGroupsResponse>

    @GET("me")
    fun getFeedTest(
            @Query("fields") fileds: String = "groups{name,picture,feed}",
            @Query("access_token") token: String
    ): Single<FacebookGroupsResponse>
}