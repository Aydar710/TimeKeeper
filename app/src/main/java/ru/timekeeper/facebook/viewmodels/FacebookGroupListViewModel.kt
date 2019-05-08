package ru.timekeeper.facebook.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.timekeeper.common.SharedPrefWrapper
import ru.timekeeper.data.network.model.facebook.grouplistremote.Datum
import ru.timekeeper.data.repository.FacebookRepository
import javax.inject.Inject

class FacebookGroupListViewModel @Inject constructor(private val repository: FacebookRepository,
                                                     private val sPref: SharedPrefWrapper)
    : ViewModel() {

    var groups: MutableLiveData<List<Datum>> = MutableLiveData()

    fun getGroups() {
        repository.getFacebookGroups(sPref.getFacebookToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    groups.value = it
                }
                        , {
                    it.printStackTrace()
                })
    }
}