package ru.timekeeper.ui.vk

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_container.*
import ru.timekeeper.R
import ru.timekeeper.adapters.VkGroupsAdapter
import ru.timekeeper.data.network.model.groupsRemote.Group

class ContainerActivity : AppCompatActivity(), VkGroupsAdapter.ListItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_vk -> onActionVkClicked()
            }
            true
        }
    }

    override fun onClick(group: Group) {
        doGroupWallTransaction(group)
    }

    private fun onActionVkClicked() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_main, VkUserGroupsFragment.newInstance(116812347))
            .commit()
    }

    private fun doGroupWallTransaction(group: Group) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.container_main, VkGroupWallFragment.newInstance(group))
            .commit()
    }
}
