package ru.timekeeper.facebook.ui


import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.timekeeper.R
import ru.timekeeper.common.App
import ru.timekeeper.common.SharedPrefWrapper
import javax.inject.Inject


class FacebookGroupListFragment : Fragment() {

    @Suppress("LateinitUsage")
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Suppress("LateinitUsage")
    @Inject
    lateinit var sharedPrefWrapper: SharedPrefWrapper

    //private var viewModel: VkGroupsFragmentViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_facebook_group_list, container, false)
        App.component.inject(this)

        return view
    }
}
