
package com.materialstudies.owl.ui.onboarding

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.materialstudies.owl.R
import com.materialstudies.owl.databinding.OnboardingTopicItemBinding
import com.materialstudies.owl.model.Topic
import com.materialstudies.owl.model.TopicDiff

class TopicsAdapter(context: Context) : ListAdapter<Topic, TopicsViewHolder>(TopicDiff) {

    private val selectedTint = context.getColor(R.color.topic_tint)
    private val selectedTopLeftCornerRadius =
        context.resources.getDimensionPixelSize(R.dimen.small_component_top_left_radius)
    private val selectedDrawable = context.getDrawable(R.drawable.ic_checkmark)!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicsViewHolder {
        val binding = OnboardingTopicItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).apply {
            root.setOnClickListener {
                it.isActivated = !it.isActivated
            }
        }
        return TopicsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicsViewHolder, position: Int) {
        holder.bind(getItem(position), selectedTint, selectedTopLeftCornerRadius, selectedDrawable)
    }

    override fun onViewRecycled(holder: TopicsViewHolder) {
        holder.itemView.rotation = 0f
    }

}

class TopicsViewHolder(
    private val binding: OnboardingTopicItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        topic: Topic,
        @ColorInt selectedTint: Int,
        @Px selectedTopLeftCornerRadius: Int,
        selectedDrawable: Drawable
    ) {
        binding.run {
            this.topic = topic
            Glide.with(topicImage)
                .asBitmap()
                .load(topic.imageUrl)
                .placeholder(R.drawable.course_image_placeholder)
                .into(
                    TopicThumbnailTarget(
                        topicImage,
                        selectedTint,
                        selectedTopLeftCornerRadius,
                        selectedDrawable
                    )
                )
            executePendingBindings()
        }
    }

}
