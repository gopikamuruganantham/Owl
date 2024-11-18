
package com.materialstudies.owl.util.transition

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View
import android.view.ViewGroup
import androidx.transition.TransitionValues
import androidx.transition.Visibility

private const val RECEDED_SCALE = 0.9f
private const val RECEDED_ALPHA = 0.9f

/**
 * A [androidx.transition.Transition] which animates the scale X & Y and alpha of a given view.
 */
class Recede : Visibility() {

    override fun onAppear(
        sceneRoot: ViewGroup,
        view: View,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator? {
        view.apply {
            alpha = RECEDED_ALPHA
            scaleX = RECEDED_SCALE
            scaleY = RECEDED_SCALE
        }
        return ObjectAnimator.ofPropertyValuesHolder(
            view,
            PropertyValuesHolder.ofFloat(
                View.SCALE_X,
                RECEDED_SCALE,
                1f
            ),
            PropertyValuesHolder.ofFloat(View.SCALE_Y, RECEDED_SCALE, 1f),
            PropertyValuesHolder.ofFloat(View.ALPHA, RECEDED_ALPHA, 1f)
        )
    }

    override fun onDisappear(
        sceneRoot: ViewGroup,
        view: View,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ) = ObjectAnimator.ofPropertyValuesHolder(
        view,
        PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, RECEDED_SCALE),
        PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, RECEDED_SCALE),
        PropertyValuesHolder.ofFloat(View.ALPHA, 1f, RECEDED_ALPHA)
    )
}
