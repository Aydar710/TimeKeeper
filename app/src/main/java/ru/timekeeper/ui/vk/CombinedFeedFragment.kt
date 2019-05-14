package ru.timekeeper.ui.vk

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_vk_group_wall.view.*
import ru.timekeeper.App
import ru.timekeeper.R
import ru.timekeeper.adapters.VkPostAdapter
import ru.timekeeper.data.network.model.groupWallRemote.Item
import ru.timekeeper.viewModels.CombinedFeedViewModel
import javax.inject.Inject

class CombinedFeedFragment : Fragment() {

    @Suppress("LateinitUsage")
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var viewModel: CombinedFeedViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_vk_group_wall, container, false)

        App.component.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[CombinedFeedViewModel::class.java]

        val recyclerView = view.recycler_vk_group_wall
        val adapter = VkPostAdapter()
        adapter.groupName = ""
        adapter.groupPhotoSource = ""

        recyclerView.adapter = adapter

        viewModel?.posts?.observe(this, Observer<List<Item>> { posts ->
            adapter.submitList(posts)
        })

        viewModel?.getCombinedFeed()
        return view
    }
}

