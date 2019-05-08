package ru.timekeeper.facebook.ui


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_facebook_group_list.view.*
import ru.timekeeper.R
import ru.timekeeper.common.App
import ru.timekeeper.common.SharedPrefWrapper
import ru.timekeeper.data.network.model.facebook.grouplistremote.Datum
import ru.timekeeper.data.repository.FacebookRepository
import ru.timekeeper.facebook.adapters.FacebookGroupsAdapter
import ru.timekeeper.facebook.viewmodels.FacebookGroupListViewModel
import javax.inject.Inject


class FacebookGroupListFragment : Fragment() {

    @Suppress("LateinitUsage")
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Suppress("LateinitUsage")
    @Inject
    lateinit var sharedPrefWrapper: SharedPrefWrapper

    private var viewModel: FacebookGroupListViewModel? = null

    var adapter : FacebookGroupsAdapter? = null

    @Inject
    lateinit var repo : FacebookRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_facebook_group_list, container, false)
        App.component.inject(this)

        viewModel = ViewModelProviders
                .of(this, viewModelFactory)[FacebookGroupListViewModel::class.java]

        val recycler = view.recycler_facebook_group_list
        adapter = FacebookGroupsAdapter()
        recycler.adapter = adapter

        viewModel?.groups?.observe(this, Observer<List<Datum>> { groups ->
            adapter?.submitList(groups)
        })
        viewModel?.getGroups()

        repo.getFeedTest(sharedPrefWrapper.getFacebookToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    print("")
                },{

                })
        return view
    }
}
