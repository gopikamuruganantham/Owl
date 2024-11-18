
package com.materialstudies.owl.ui.learn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.materialstudies.owl.R
import com.materialstudies.owl.databinding.FragmentLearnBinding
import com.materialstudies.owl.model.CourseRepo
import com.materialstudies.owl.model.courses
import com.materialstudies.owl.ui.lessons.LessonsSheetFragment
import com.materialstudies.owl.util.transition.DiagonalSlide
import com.materialstudies.owl.util.transition.MaterialContainerTransition
import com.materialstudies.owl.util.loadListener
import java.util.concurrent.TimeUnit

/**
 * A [Fragment] displaying the learn screen.
 */
class LearnFragment : Fragment() {

    private val args: LearnFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val course = CourseRepo.getCourse(args.courseId)
        val binding = FragmentLearnBinding.inflate(inflater, container, false).apply {
            this.course = course
            imageLoadListener = loadListener {
                startPostponedEnterTransition()
            }
            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
            alsoLikeList.adapter = RelatedAdapter().apply {
                submitList(courses)
            }
        }
        (childFragmentManager.findFragmentById(R.id.lessons_sheet) as? LessonsSheetFragment)?.let {
            it.course = course
        }
        postponeEnterTransition(1000L, TimeUnit.MILLISECONDS)
        val interp = AnimationUtils.loadInterpolator(
            context,
            android.R.interpolator.fast_out_slow_in
        )
        sharedElementEnterTransition = MaterialContainerTransition(R.id.scroll).apply {
            duration = 400L
            interpolator = interp
        }
        enterTransition = DiagonalSlide().apply {
            addTarget(R.id.lessons_sheet)
            startDelay = 200L
            duration = 200L
            interpolator = interp
        }
        sharedElementReturnTransition = MaterialContainerTransition().apply {
            duration = 300L
            interpolator = interp
        }
        returnTransition = DiagonalSlide().apply {
            addTarget(R.id.lessons_sheet)
            duration = 100L
            interpolator = interp
        }
        return binding.root
    }
}
