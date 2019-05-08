package ru.timekeeper.vkontakte.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_vk_groups.view.*
import ru.timekeeper.R
import ru.timekeeper.common.App
import ru.timekeeper.common.SharedPrefWrapper
import ru.timekeeper.common.ui.MainActivity
import ru.timekeeper.data.network.model.vkontakte.groupsRemote.Group
import ru.timekeeper.vkontakte.adapters.VkGroupsAdapter
import ru.timekeeper.vkontakte.viewmodels.VkGroupsFragmentViewModel
import javax.inject.Inject

class VkGroupListFragment : Fragment() {


    var adapter: VkGroupsAdapter? = null

    @Suppress("LateinitUsage")
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Suppress("LateinitUsage")
    @Inject
    lateinit var sharedPrefWrapper: SharedPrefWrapper

    private var viewModel: VkGroupsFragmentViewModel? = null

    companion object {
        private val ARG_USER_ID = "user_id"
        fun newInstance(userId: Int): VkGroupListFragment {
            val args: Bundle = Bundle()
            args.putInt(ARG_USER_ID, userId)
            val fragment: VkGroupListFragment = VkGroupListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_vk_groups, container, false)
        App.component.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[VkGroupsFragmentViewModel::class.java]

        val recyclerView = view.recycler_user_groups
        val fragmentActivity: MainActivity = activity as MainActivity
        adapter = VkGroupsAdapter(fragmentActivity)
        val userId: String = arguments?.getInt(ARG_USER_ID).toString()
        val token = sharedPrefWrapper.getVkToken()
        recyclerView.adapter = adapter

        viewModel?.groups?.observe(this, Observer<List<Group>> { groups ->
            adapter?.submitList(groups)
        })

        viewModel?.getUserGroups(token, userId)
        return view
    }
}
