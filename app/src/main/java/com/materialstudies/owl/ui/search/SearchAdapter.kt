

package com.materialstudies.owl.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.materialstudies.owl.databinding.SearchItemBinding
import com.materialstudies.owl.model.Topic
import com.materialstudies.owl.model.TopicDiff

class SearchAdapter : ListAdapter<Topic, SearchViewHolder>(TopicDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class SearchViewHolder(
    private val binding: SearchItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(topic: Topic) {
        binding.run {
            this.topic = topic
            executePendingBindings()
        }
    }

}
