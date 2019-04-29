package ru.timekeeper.ui.vk

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_vk_group_wall.view.*
import ru.timekeeper.*
import ru.timekeeper.adapters.VkPostAdapter
import ru.timekeeper.data.network.model.groupWallRemote.Item
import ru.timekeeper.data.network.model.groupsRemote.Group
import ru.timekeeper.viewModels.VkGroupWallViewModel
import javax.inject.Inject

class VkGroupWallFragment : Fragment() {


    private var sPref: SharedPreferences? = null

    @Inject
    lateinit var sharedPrefWrapper: SharedPrefWrapper

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var viewModel: VkGroupWallViewModel? = null

    companion object {
        private var ARG_GROUP_ID = "group_id"
        private var ARG_GROUP_NAME = "group_name"
        private var ARG_GROUP_PHOTO_SRC = "group_photo_src"
        fun newInstance(group: Group): VkGroupWallFragment {
            val args = Bundle().apply {
                putInt(ARG_GROUP_ID, group.id ?: -1)
                putString(ARG_GROUP_NAME, group.name)
                putString(ARG_GROUP_PHOTO_SRC, group.photo100)
            }
            val fragment = VkGroupWallFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.component.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[VkGroupWallViewModel::class.java]

        val view = inflater.inflate(R.layout.fragment_vk_group_wall, container, false)
        val recyclerView = view.recycler_vk_group_wall
        val adapter = VkPostAdapter()

        val groupId: String = "-" + arguments?.getInt(ARG_GROUP_ID).toString()
        recyclerView.adapter = adapter
        val token = sharedPrefWrapper.getTokenFromPreferences()

        viewModel?.postsLiveData?.observe(this, Observer<List<Item>> { posts ->
            adapter.submitList(posts)
        })
        return view
    }

    fun getTokenFromPreferences(): String {
        //sPref = activity?.getSharedPreferences(SHARED_PREF_FILENAME, AppCompatActivity.MODE_PRIVATE)
        sPref = App.component.provideApp().getSharedPreferences(SHARED_PREF_FILENAME, AppCompatActivity.MODE_PRIVATE)
        val token: String? = sPref?.getString(SHARED_PREF_TOKEN_KEY, "")
        return token ?: ""
    }

}
