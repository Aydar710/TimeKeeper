package ru.timekeeper.vkontakte.adapters

import android.support.v7.util.DiffUtil
import ru.timekeeper.data.network.model.vkontakte.groupsRemote.Group

class GroupItemDiffCallback : DiffUtil.ItemCallback<Group>() {
    override fun areItemsTheSame(oldGroup: Group, newGroup: Group): Boolean =
            oldGroup.id == newGroup.id


    override fun areContentsTheSame(oldGroup: Group, newGroup: Group): Boolean =
            oldGroup == newGroup
}
