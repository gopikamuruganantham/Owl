

package com.materialstudies.owl.ui.lessons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.materialstudies.owl.R
import com.materialstudies.owl.databinding.FragmentLessonBinding
import com.materialstudies.owl.model.CourseRepo
import com.materialstudies.owl.model.lessons
import java.util.concurrent.TimeUnit

/**
 * A [Fragment] displaying a lesson.
 */
class LessonFragment : Fragment() {

    private val args: LessonFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLessonBinding.inflate(inflater, container, false).apply {
            lesson = lessons.first()
            steps.apply {
                adapter = StepsAdapter(lessons, context)
                smoothScrollToPosition(args.stepNumber)
            }
            collapse.setOnClickListener {
                it.findNavController().popBackStack()
            }
        }
        (childFragmentManager.findFragmentById(R.id.lessons_sheet) as? LessonsSheetFragment)?.let {
            it.course = CourseRepo.getCourse(args.courseId)
        }
        return binding.root
    }
}
