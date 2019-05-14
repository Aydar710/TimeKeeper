package ru.timekeeper.viewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.google.firebase.firestore.CollectionReference
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.timekeeper.SharedPrefWrapper
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

    fun getCombinedFeed() {
        favoriteIds.add("119247232")
        favoriteIds.add("35488145")
        favoriteIds.add("41696672")
        favoriteIds.add("57846937")
        favoriteIds.add("22822305")
        vkRepository.getCombinedFeed(favoriteIds, token = sPref.getTokenFromPreferences())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val postsValue : MutableList<Item> = mutableListOf()
                posts.value?.let {
                    postsValue.addAll(it)
                }
                postsValue.addAll(it)
                posts.value = postsValue
                print("")
            }, {
                it.printStackTrace()
            })
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
}
