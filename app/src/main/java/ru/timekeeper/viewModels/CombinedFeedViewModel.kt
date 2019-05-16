package ru.timekeeper.viewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.google.firebase.firestore.CollectionReference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ru.timekeeper.data.repository.SharedPrefWrapper
import ru.timekeeper.data.network.model.groupWallRemote.Item
import ru.timekeeper.data.repository.VkRepository
import javax.inject.Inject

class CombinedFeedViewModel @Inject constructor(
        private val vkRepository: VkRepository,
        private val idsCollection: CollectionReference,
        private val sPref: SharedPrefWrapper
) : ViewModel() {

    private var favoriteIds = mutableListOf<String>()

    val posts: MutableLiveData<List<Item>> = MutableLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()

    fun getCombinedFeed() {
        favoriteIds.add("41696672")
        favoriteIds.add("119247232")
        favoriteIds.add("172142411")
        val disposable = vkRepository.getCombinedFeed(favoriteIds, token = sPref.getToken())
                .doOnSubscribe {
                    isLoading.postValue(true)
                }
                .doOnComplete {
                    isLoading.postValue(false)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val postsValue: MutableList<Item> = mutableListOf()
                    posts.value?.let {
                        postsValue.addAll(it)
                    }
                    postsValue.addAll(it)
                    posts.value = postsValue
                    print("")
                }, {
                    it.printStackTrace()
                })

        compositeDisposable.add(disposable)
    }

    fun getFavoriteIds() {
        idsCollection
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val data = document.data
                        val id = data["id"]
                        favoriteIds.add("$id")
                    }
                }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
