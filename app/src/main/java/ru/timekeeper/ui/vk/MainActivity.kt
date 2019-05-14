package ru.timekeeper.ui.vk

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.timekeeper.R
import ru.timekeeper.USER_ID
import ru.timekeeper.adapters.VkGroupsAdapter
import ru.timekeeper.data.network.model.groupsRemote.Group

class MainActivity : AppCompatActivity(), VkGroupsAdapter.ListItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_vk -> onActionVkClicked()
                R.id.action_favorites -> doCombinedFeedTransaction()
            }
            true
        }
    }

    override fun onVkGroupClicked(group: Group) {
        doGroupWallTransaction(group)
    }

    private fun onActionVkClicked() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_main, VkGroupsFragment.newInstance(USER_ID))
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
