package ru.timekeeper.ui.vk

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_user_groups.view.*
import ru.timekeeper.App
import ru.timekeeper.R
import ru.timekeeper.SharedPrefWrapper
import ru.timekeeper.adapters.VkGroupsAdapter
import ru.timekeeper.data.network.model.groupsRemote.Group
import ru.timekeeper.viewModels.VkUserGroupsFragmentViewModel
import javax.inject.Inject

class VkUserGroupsFragment : Fragment() {


    var adapter: VkGroupsAdapter? = null

    @Suppress("LateinitUsage")
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Suppress("LateinitUsage")
    @Inject
    lateinit var sharedPrefWrapper: SharedPrefWrapper

    private var viewModel: VkUserGroupsFragmentViewModel? = null

    companion object {
        private val ARG_USER_ID = "user_id"
        fun newInstance(userId: Int): VkUserGroupsFragment {
            val args: Bundle = Bundle()
            args.putInt(ARG_USER_ID, userId)
            val fragment: VkUserGroupsFragment = VkUserGroupsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_user_groups, container, false)
        App.component.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[VkUserGroupsFragmentViewModel::class.java]

        val recyclerView = view.recycler_user_groups
        val fragmentActivity: ContainerActivity = activity as ContainerActivity
        adapter = VkGroupsAdapter(fragmentActivity)
        val userId: String = arguments?.getInt(ARG_USER_ID).toString()
        recyclerView.adapter = adapter
        val token = sharedPrefWrapper.getTokenFromPreferences()

        viewModel?.groupsLiveData?.observe(this, Observer<List<Group>> { groups ->
            adapter?.submitList(groups)
        })

        return view
    }
}
