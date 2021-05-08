package ru.timekeeper.adapters

import androidx.recyclerview.widget.DiffUtil
import ru.timekeeper.data.network.model.groupsRemote.Group

class GroupItemDiffCallback : DiffUtil.ItemCallback<Group>() {

    override fun areItemsTheSame(oldGroup: Group, newGroup: Group): Boolean =
        oldGroup.id == newGroup.id


    override fun areContentsTheSame(oldGroup: Group, newGroup: Group): Boolean =
        oldGroup.equals(newGroup)
}
