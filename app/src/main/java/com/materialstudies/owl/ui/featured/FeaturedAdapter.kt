

package com.materialstudies.owl.ui.featured

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.materialstudies.owl.databinding.FeaturedItemBinding
import com.materialstudies.owl.model.Course
import com.materialstudies.owl.model.CourseDiff
import com.materialstudies.owl.model.CourseId

class FeaturedAdapter(
    private val onClick: CourseViewClick
) : ListAdapter<Course, FeaturedViewHolder>(CourseDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedViewHolder {
        return FeaturedViewHolder(
            FeaturedItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FeaturedViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }
}

interface CourseViewClick {
    fun onClick(view: View, courseId: CourseId)
}

class FeaturedViewHolder(
    private val binding: FeaturedItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(course: Course, onClick: CourseViewClick) {
        binding.run {
            this.course = course
            clickHandler = onClick
            executePendingBindings()
        }
    }
}
