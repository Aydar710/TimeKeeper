package ru.timekeeper.ui.vk

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.*
import kotlinx.android.synthetic.main.fragment_vk_groups.view.*
import ru.timekeeper.App
import ru.timekeeper.PAGINATION_SIZE_GROUPS
import ru.timekeeper.R
import ru.timekeeper.adapters.VkGroupsAdapter
import ru.timekeeper.data.network.model.groupsRemote.Group
import ru.timekeeper.viewModels.VkGroupsViewModel
import javax.inject.Inject

class VkGroupsFragment : androidx.fragment.app.Fragment() {

    lateinit var adapter: VkGroupsAdapter

    @Suppress("LateinitUsage")
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Suppress("LateinitUsage")
    lateinit var viewModel: VkGroupsViewModel

    var userId: String = ""

    companion object {
        private val ARG_USER_ID = "user_id"
        fun newInstance(userId: Int): VkGroupsFragment {
            val args: Bundle = Bundle()
            args.putInt(ARG_USER_ID, userId)
            val fragment: VkGroupsFragment = VkGroupsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(ru.timekeeper.R.layout.fragment_vk_groups, container, false)
        App.component.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[VkGroupsViewModel::class.java]
        val recyclerView = view.recycler_user_groups
        val fragmentActivity: MainActivity = activity as MainActivity
        adapter = VkGroupsAdapter(fragmentActivity) {
            onImgFavoriteClicked(it)
        }
        userId = arguments?.getInt(ARG_USER_ID).toString()
        recyclerView.adapter = adapter

        val manager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        recyclerView.layoutManager = manager
        viewModel.groups.observe(this, Observer<List<Group>> { groups ->
            adapter.submitList(groups)
            adapter.notifyDataSetChanged()
        })
        viewModel.isLoading.observe(this, Observer<Boolean> { isLoading ->
            isLoading?.let {
                if (it)
                    view.progress_bar_groups.visibility = View.VISIBLE
                else
                    view.progress_bar_groups.visibility = View.GONE
            }
        })

        recyclerView.addOnScrollListener(object : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
            private var currentPage: Int = 0
            private var isLastPage = false

            override fun onScrolled(recyclerView: androidx.recyclerview.widget.RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = manager.childCount
                val totalItemCount = manager.itemCount
                val firstVisibleItemPosition = manager.findFirstVisibleItemPosition()

                if (!isLastPage) {
                    if (visibleItemCount + firstVisibleItemPosition == totalItemCount
                            && firstVisibleItemPosition >= 0
                    ) {
                        viewModel.loadNextGroups(userId, ++currentPage, PAGINATION_SIZE_GROUPS)

                    }
                }
            }
        })

        viewModel.getGroups(userId)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_filter -> {
                viewModel.getFavoriteGroups(userId)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onImgFavoriteClicked(groupId: Int) {
        viewModel.onStarClicked(groupId)
    }
}
