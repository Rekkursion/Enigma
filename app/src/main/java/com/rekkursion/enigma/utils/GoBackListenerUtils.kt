package com.rekkursion.enigma.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.rekkursion.enigma.listeners.OnFragmentGoBackListener

/**
 * Original author: https://www.jianshu.com/u/019f1cbb16e2
 * Original Source: https://www.jianshu.com/p/fff1ef649fc0
 */
object GoBackListenerUtils {
    /**
     * distribute the event to child-fragments, if none of all children handle the event, try pop-stack-back method
     *
     * @return if the go-back event has been handled, return true
     * @see handleGoBack(Fragment)
     * @see handleGoBack(FragmentActivity)
     */
    fun handleGoBack(fragmentManager: FragmentManager): Boolean {
        val fragments = fragmentManager.fragments

        if (fragments.isNotEmpty()) {
            for (i in (fragments.size - 1) downTo 0) {
                val child = fragments[i]
                if (isFragmentGoBackHandled(child))
                    return true
            }
        }

        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
            return true
        }

        return false
    }

    fun handleGoBack(fragment: Fragment): Boolean = handleGoBack(fragment.childFragmentManager)

    fun handleGoBack(fragmentActivity: FragmentActivity): Boolean = handleGoBack(fragmentActivity.supportFragmentManager)

    // check if the fragment has handled the go-back event
    fun isFragmentGoBackHandled(fragment: Fragment): Boolean {
        return fragment.isVisible &&
                fragment.userVisibleHint &&
                fragment is OnFragmentGoBackListener &&
                (fragment as OnFragmentGoBackListener).onGoBack()
    }
}