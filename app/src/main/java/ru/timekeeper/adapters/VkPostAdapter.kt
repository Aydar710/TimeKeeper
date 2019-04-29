package ru.timekeeper.adapters

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_vk_group_wall.*
import ru.timekeeper.R
import ru.timekeeper.data.network.model.groupWallRemote.Item

class VkPostAdapter : ListAdapter<Item, VkPostAdapter.PostHolder>(PostItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PostHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_vk_group_wall, parent, false)
        return PostHolder(view)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PostHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(post: Item) {
            //txt_vk_group_name.text = post...
            txt_vk_post_date.text = post.date.toString()
            txt_vk_post_text.text = post.text
            txt_vk_post_likes.text = post.likes?.count.toString()
            txt_vk_post_comments.text = post.comments?.count.toString()
            txt_vk_post_repost.text = post.reposts?.count.toString()

            Picasso.get()
                    .load(
                            getImageSize(post)?.let {
                                post.attachments?.get(0)?.photo?.sizes?.get(it)?.url
                            }
                    )
                    .into(img_vk_post_photo)
        }
    }

    fun getImageSize(post: Item): Int? =
            post.attachments?.get(0)?.photo?.sizes?.size?.minus(1)

}