package ru.timekeeper.adapters

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_vk_group_wall.*
import ru.timekeeper.K_LETTER
import ru.timekeeper.M_LETTER
import ru.timekeeper.data.network.model.groupWallRemote.Item

class VkPostAdapter : ListAdapter<Item, VkPostAdapter.PostHolder>(PostItemDiffCallback()) {

    var groupPhotoSource : String? = null
    var groupName : String? = null

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PostHolder {
        val view = LayoutInflater.from(parent.context).inflate(ru.timekeeper.R.layout.card_vk_group_wall, parent, false)
        return PostHolder(view)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PostHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(post: Item) {
            txt_vk_group_name.text = groupName
            txt_vk_post_date.text = post.date.toString()
            if (post.text?.length != 0) {
                txt_vk_post_text.visibility = View.VISIBLE
                txt_vk_post_text.text = post.text
            }

            txt_vk_post_likes.text = ellipsize(post.likes?.count.toString())
            txt_vk_post_comments.text = ellipsize(post.comments?.count.toString())
            txt_vk_post_repost.text = ellipsize(post.reposts?.count.toString())
            txt_vk_post_views.text = ellipsize(post.views?.count.toString())


            val postImageUrl = getImageSize(post)?.let { post.attachments?.get(0)?.photo?.sizes?.get(it)?.url }
            postImageUrl?.let {
                Picasso.get()
                        .load(it)
                        .into(img_vk_post_photo, object : Callback {
                            override fun onSuccess() {
                                img_vk_post_photo.visibility = View.VISIBLE
                            }

                            override fun onError(e: Exception?) {
                                img_vk_post_photo.visibility = View.GONE
                            }
                        })
            }

            Picasso.get()
                    .load(groupPhotoSource)
                    .into(img_vk_group_photo)
        }
    }

    fun getImageSize(post: Item): Int? =
            post.attachments?.get(0)?.photo?.sizes?.size?.minus(1)

    fun ellipsize(text: CharSequence): CharSequence =
            when (text.length) {
                in 1..3 -> text
                4 -> text[0] + K_LETTER
                5 -> text.subSequence(0, 1).toString() + K_LETTER
                6 -> text.subSequence(0, 2).toString() + K_LETTER
                else -> text[0] + M_LETTER
            }
}
