package ru.timekeeper.ui.vk

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.timekeeper.App
import ru.timekeeper.R
import ru.timekeeper.adapters.VkGroupsAdapter
import ru.timekeeper.data.network.model.groupsRemote.Group
import ru.timekeeper.data.repository.SharedPrefWrapper
import javax.inject.Inject

class MainActivity : AppCompatActivity(), VkGroupsAdapter.ListItemClickListener {

    @Inject
    lateinit var sPref: SharedPrefWrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        App.component.inject(this)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_vk -> onActionVkClicked()
                R.id.action_favorites -> doCombinedFeedTransaction()
            }
            true
        }

        onActionVkClicked()
    }

    override fun onVkGroupClicked(group: Group) {
        doGroupWallTransaction(group)
    }

    private fun onActionVkClicked() {
        val currentUserId = sPref.getUserId()

        if (currentUserId != -1)
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container_main, VkGroupsFragment.newInstance(currentUserId))
                    .commit()

    }

    private fun doGroupWallTransaction(group: Group) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
                .replace(R.id.container_main, VkGroupWallFragment.newInstance(group))
                .commit()
    }

    private fun doCombinedFeedTransaction() {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
                .replace(R.id.container_main, CombinedFeedFragment())
                .commit()
    }
}
