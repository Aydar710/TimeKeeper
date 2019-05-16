package ru.timekeeper.ui.vk

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import kotlinx.android.synthetic.main.fragment_vk_group_wall.view.*
import ru.timekeeper.App
import ru.timekeeper.PAGINATION_SIZE
import ru.timekeeper.R
import ru.timekeeper.TOTAL_ITEM_COUNT_MORE_THAN
import ru.timekeeper.adapters.VkPostAdapter
import ru.timekeeper.data.network.model.groupWallRemote.Item
import ru.timekeeper.data.network.model.groupsRemote.Group
import ru.timekeeper.viewModels.VkGroupWallViewModel
import javax.inject.Inject

class VkGroupWallFragment : Fragment() {

    @Suppress("LateinitUsage")
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

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.component.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[VkGroupWallViewModel::class.java]

        val view = inflater.inflate(R.layout.fragment_vk_group_wall, container, false)
        val recyclerView = view.recycler_vk_group_wall
        val adapter = VkPostAdapter()
        adapter.groupName = arguments?.getString(ARG_GROUP_NAME) ?: ""
        adapter.groupPhotoSource = arguments?.getString(ARG_GROUP_PHOTO_SRC) ?: ""

        val groupId: String = "-" + arguments?.getInt(ARG_GROUP_ID).toString()
        recyclerView.adapter = adapter

        val manager = LinearLayoutManager(activity)
        recyclerView.layoutManager = manager

        viewModel?.posts?.observe(this, Observer<List<Item>> { posts ->
            adapter.submitList(posts)
        })

        viewModel?.isLoading?.observe(this, Observer<Boolean> { isLoading ->
            isLoading?.let {
                if (it)
                    view.progress_bar_wall.visibility = View.VISIBLE
                else
                    view.progress_bar_wall.visibility = View.GONE
            }
        })

        viewModel?.loadGroupWall(groupId)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var currentPage: Int = 0
            private var isLastPage = false

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = manager.childCount
                val totalItemCount = manager.itemCount
                val firstVisibleItemPosition = manager.findFirstVisibleItemPosition()

                if (!isLastPage) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= TOTAL_ITEM_COUNT_MORE_THAN
                    ) {

                        viewModel?.loadNextPosts(groupId, ++currentPage, PAGINATION_SIZE)

                    }
                }
            }
        })
        return view
    }
}
