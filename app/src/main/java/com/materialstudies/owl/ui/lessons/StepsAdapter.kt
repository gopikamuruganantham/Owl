

package com.materialstudies.owl.ui.lessons

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.materialstudies.owl.R
import com.materialstudies.owl.databinding.StepItemBinding
import com.materialstudies.owl.model.Lesson

class StepsAdapter(
    lessons: List<Lesson>,
    context: Context
) : ListAdapter<Step, StepViewHolder>(StepDiff) {

    init {
        val steps = lessons.mapIndexed { step, _ ->
            Step(
                context.getString(R.string.step_num, step + 1),
                when (step % 3) {
                    0 -> context.getString(R.string.step_0)
                    1 -> context.getString(R.string.step_1)
                    else -> context.getString(R.string.step_2)
                }
            )
        }
        submitList(steps)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val binding = StepItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StepViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class StepViewHolder(private val binding: StepItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(step: Step) {
        binding.run {
            this.step = step
            executePendingBindings()
        }
    }
}

data class Step(
    val title: String,
    val body: String
)

object StepDiff : DiffUtil.ItemCallback<Step>() {
    override fun areItemsTheSame(oldItem: Step, newItem: Step) = oldItem.title == newItem.title
    override fun areContentsTheSame(oldItem: Step, newItem: Step) = oldItem == newItem
}
