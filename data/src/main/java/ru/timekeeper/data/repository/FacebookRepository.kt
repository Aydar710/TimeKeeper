package ru.timekeeper.data.repository

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.timekeeper.data.network.model.facebook.grouplistremote.Datum
import ru.timekeeper.data.service.FacebookService

class FacebookRepository(private val facebookService: FacebookService) {

    fun getFacebookGroups(token: String): Single<List<Datum>> =
            facebookService.getFacebookGroups(token = token)
                    .subscribeOn(Schedulers.io())
                    .map {
                        it.groups?.data
                    }

    fun getFeedTest(token: String) =
            facebookService.getFeedTest(token = token)
                    .subscribeOn(Schedulers.io())

}
