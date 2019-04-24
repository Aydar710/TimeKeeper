package ru.timekeeper.ui.vk

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_user_groups.view.*
import ru.timekeeper.App
import ru.timekeeper.R
import ru.timekeeper.SHARED_PREF_FILENAME
import ru.timekeeper.SHARED_PREF_TOKEN_KEY
import ru.timekeeper.adapters.GroupsAdapter
import ru.timekeeper.data.repository.VkRepository
import ru.timekeeper.viewModels.UserGroupsFragmentViewModel
import javax.inject.Inject

class UserGroupsFragment : Fragment() {

    @Inject
    lateinit var repository: VkRepository
    lateinit var adapter: GroupsAdapter
    lateinit var sPref: SharedPreferences

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: UserGroupsFragmentViewModel

    companion object {
        private val ARG_USER_ID = "user_id"
        fun newInstance(userId: Int): UserGroupsFragment {
            val args: Bundle = Bundle()
            args.putInt(ARG_USER_ID, userId)
            val fragment: UserGroupsFragment = UserGroupsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_user_groups, container, false)
        App.component.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[UserGroupsFragmentViewModel::class.java]

        val recyclerView = view.recycler_user_groups
        val fragmentActivity: ContainerActivity = activity as ContainerActivity
        adapter = GroupsAdapter(fragmentActivity)
        val userId: String = arguments?.getInt(ARG_USER_ID).toString()
        recyclerView.adapter = adapter
        val token = getTokenFromPreferences()

        /*repository.getUsersGroups(userId, count = "30", token = token)*/
        /*    .observeOn(AndroidSchedulers.mainThread())*/
        /*    .subscribe(*/
        /*        {*/
        /*            adapter.submitList(it)*/
        /*        },*/
        /*        {*/
        /*            it.printStackTrace()*/
        /*        }*/
        /*    )*/



        /*viewModel.groupsLiveData.observe(this, Observer<List<Group>> { groups ->
            adapter.submitList(groups)
        })

        viewModel.getUserGroups(userId, token)*/
        return view
    }

    fun getTokenFromPreferences(): String {
        sPref = App.component.provideApp().getSharedPreferences(SHARED_PREF_FILENAME, AppCompatActivity.MODE_PRIVATE)
        return sPref.getString(SHARED_PREF_TOKEN_KEY, "")
    }
}