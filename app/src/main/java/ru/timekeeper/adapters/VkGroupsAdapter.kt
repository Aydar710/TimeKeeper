package ru.timekeeper.adapters

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_vk_group.*
import kotlinx.android.synthetic.main.card_vk_group.view.*
import ru.timekeeper.R
import ru.timekeeper.data.network.model.groupsRemote.Group

class VkGroupsAdapter(private val listItemClickListener: ListItemClickListener,
                      private val favoritesClickListener: (Int) -> Unit)
    : ListAdapter<Group, VkGroupsAdapter.GroupHolder>(GroupItemDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): GroupHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_vk_group, parent, false)
        return GroupHolder(view)
    }

    override fun onBindViewHolder(holder: GroupHolder, position: Int) {
        val group = getItem(position)
        group?.let {
            holder.bind(it)
        }
    }

    interface ListItemClickListener {
        fun onVkGroupClicked(group: Group)
    }


    inner class GroupHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {
        var imgGroup = containerView.img_group

        fun bind(group: Group) {
            txt_group_name.text = group.name

            if (group.isFavorite){
                img_favorite.setImageResource(R.drawable.ic_star_filled)
            }else{
                img_favorite.setImageResource(R.drawable.ic_star_border)
            }

            containerView.setOnClickListener {
                listItemClickListener.onVkGroupClicked(group)
            }
            img_favorite.setOnClickListener {
                group.id?.let { GroupId -> favoritesClickListener(GroupId) }
            }

            Picasso.get()
                    .load(group.photo100)
                    .into(imgGroup)

        }
    }
}
