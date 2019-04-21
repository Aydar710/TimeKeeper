package ru.timekeeper.ui.vk

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_container.*
import ru.timekeeper.R
import ru.timekeeper.adapters.GroupsAdapter
import ru.timekeeper.data.network.model.groupsRemote.Group

class ContainerActivity : AppCompatActivity(), GroupsAdapter.ListItemClickListener {

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
    }

    fun onActionVkClicked() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container_main, UserGroupsFragment.newInstance(116812347))
                .commit()
    }
}
