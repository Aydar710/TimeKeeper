package ru.timekeeper.facebook.adapters

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_facebook_group_list.*
import ru.timekeeper.R
import ru.timekeeper.data.network.model.facebook.grouplistremote.Datum

class FacebookGroupsAdapter
    : ListAdapter<Datum, FacebookGroupsAdapter.DatumHolder>(DatumItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): DatumHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_facebook_group_list,
                parent, false)
        return DatumHolder(view)
    }

    override fun onBindViewHolder(holder: DatumHolder, position: Int) {
        val group = getItem(position)
        holder.bind(group)
    }


    inner class DatumHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(group: Datum) {
            txt_facebook_group_name.text = group.name

            Picasso.get()
                    .load(group.picture?.data?.url)
                    .into(img_facebook_group)
        }
    }
}