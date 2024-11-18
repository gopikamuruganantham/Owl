
package com.materialstudies.owl.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnNextLayout
import androidx.fragment.app.Fragment
import com.materialstudies.owl.databinding.FragmentSearchBinding
import com.materialstudies.owl.model.topics
import com.materialstudies.owl.util.SpringAddItemAnimator

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchBinding.inflate(inflater, container, false).apply {
            searchResults.apply {
                itemAnimator = SpringAddItemAnimator()
                adapter = SearchAdapter().apply {
                    // add data after layout so that animations run
                    doOnNextLayout {
                        submitList(topics)
                    }
                }
            }
        }
        return binding.root
    }
}
