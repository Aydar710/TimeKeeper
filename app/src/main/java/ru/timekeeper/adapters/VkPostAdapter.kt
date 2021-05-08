package ru.timekeeper.adapters

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_vk_group_wall.*
import ru.timekeeper.K_LETTER
import ru.timekeeper.M_LETTER
import ru.timekeeper.R
import ru.timekeeper.data.network.model.groupWallRemote.Item
import java.text.SimpleDateFormat
import java.util.*

class VkPostAdapter : ListAdapter<Item, VkPostAdapter.PostHolder>(PostItemDiffCallback()) {

    var groupPhotoSource: String? = null
    var groupName: String? = null
    lateinit var onImgLikeClickListener: (Int, String, String, Boolean) -> Unit
    lateinit var onImgRepostClickListener: (Int, Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PostHolder {
        val view = LayoutInflater.from(parent.context).inflate(ru.timekeeper.R.layout.card_vk_group_wall, parent, false)
        return PostHolder(view)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PostHolder(override val containerView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(post: Item) {
            txt_vk_group_name.text = post.groupName

            val simpleDateFormat = SimpleDateFormat("d MMMM 'в' HH:mm", Locale.forLanguageTag("RU"))
            var date = ""
            post.date?.let {
                date = simpleDateFormat.format(Date().time - it)
            }
            txt_vk_post_date.text = date
            if (post.text?.length != 0) {
                txt_vk_post_text.visibility = View.VISIBLE
                txt_vk_post_text.text = post.text
            } else {
                txt_vk_post_text.visibility = View.GONE
            }

            txt_vk_post_likes.text = ellipsize(post.likes?.count.toString())
            txt_vk_post_comments.text = ellipsize(post.comments?.count.toString())
            txt_vk_post_repost.text = ellipsize(post.reposts?.count.toString())
            txt_vk_post_views.text = ellipsize(post.views?.count.toString())

            if (post.likes?.userLikes == 1)
                img_vk_post_like.setImageResource(R.drawable.ic_like_filled)
            else
                img_vk_post_like.setImageResource(R.drawable.ic_like_border)

            img_vk_post_like.setOnClickListener {
                post.id?.let { postId ->
                    post.postType?.let { postType ->
                        val isPostLiked = post.likes?.userLikes == 1
                        post.groupId?.let { groupId ->
                            onImgLikeClickListener(
                                postId,
                                postType,
                                "-$groupId",
                                isPostLiked
                            )
                        }
                    }
                }
            }

            img_vk_post_repost.setOnClickListener {
                post.groupId?.toInt()?.let { groupId ->
                    post.id?.let { postId -> onImgRepostClickListener(groupId, postId) }
                }
            }

            val postImageUrl = getImageSize(post)?.let { post.attachments?.get(0)?.photo?.sizes?.get(it)?.url }
            Picasso.get().invalidate(postImageUrl)
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

            //Picasso.get().invalidate(post.groupPhoto)
            Picasso.get()
                .load(post.groupPhoto)
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
