package ru.timekeeper.adapters

import android.support.v7.util.DiffUtil
import ru.timekeeper.data.network.model.groupsRemote.Group

class GroupItemDiffCallback : DiffUtil.ItemCallback<Group>() {
    override fun areItemsTheSame(oldGroup: Group, newGroup: Group): Boolean {
        return oldGroup.id == newGroup.id
    }

    override fun areContentsTheSame(oldGroup: Group, newGroup: Group): Boolean {
        return oldGroup == newGroup
    }
}
