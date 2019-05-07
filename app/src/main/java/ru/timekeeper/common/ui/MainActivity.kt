package ru.timekeeper.common.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.timekeeper.R
import ru.timekeeper.common.USER_ID
import ru.timekeeper.data.network.model.vkontakte.groupsRemote.Group
import ru.timekeeper.facebook.ui.FacebookGroupListFragment
import ru.timekeeper.vkontakte.adapters.VkGroupsAdapter
import ru.timekeeper.vkontakte.ui.VkGroupWallFragment
import ru.timekeeper.vkontakte.ui.VkGroupListFragment

class MainActivity : AppCompatActivity(), VkGroupsAdapter.ListItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_vk -> onActionVkClicked()
                R.id.action_facebook -> onActionFacebookClicked()
            }
            true
        }
    }

    override fun onVkGroupClicked(group: Group) {
        doGroupWallTransaction(group)
    }

    private fun onActionVkClicked() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_main, VkGroupListFragment.newInstance(USER_ID))
            .commit()
    }

    private fun onActionFacebookClicked(){
        supportFragmentManager.beginTransaction()
                .replace(R.id.container_main, FacebookGroupListFragment())
                .commit()
    }

    private fun doGroupWallTransaction(group: Group) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.container_main, VkGroupWallFragment.newInstance(group))
            .commit()
    }
}
