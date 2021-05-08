package ru.timekeeper.adapters

import androidx.recyclerview.widget.DiffUtil
import ru.timekeeper.data.network.model.groupWallRemote.Item

class PostItemDiffCallback : DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem == newItem

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem.id == newItem.id
}
