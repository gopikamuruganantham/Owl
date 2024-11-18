

package com.materialstudies.owl.ui.learn

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.materialstudies.owl.databinding.RelatedCourseItemBinding
import com.materialstudies.owl.model.Course
import com.materialstudies.owl.model.CourseDiff

class RelatedAdapter : ListAdapter<Course, RelatedViewHolder>(CourseDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatedViewHolder {
        val binding = RelatedCourseItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RelatedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RelatedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class RelatedViewHolder(
    private val binding: RelatedCourseItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(course: Course) {
        binding.run {
            this.course = course
            executePendingBindings()
        }
    }

}
