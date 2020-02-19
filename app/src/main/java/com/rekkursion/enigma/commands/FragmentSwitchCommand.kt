package com.rekkursion.enigma.commands

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentSwitchCommand(supportFragmentManager: FragmentManager): FragmentCommand(supportFragmentManager) {
    override fun execute(vararg args: Any?) {
        val containerViewId = args[0] as Int
        val fragment = args[1] as Fragment

        if (mCurrentShowingFragment == null || fragment != mCurrentShowingFragment) {
            if (!fragment.isAdded)
                if (mSupportFragmentManager.fragments.isEmpty())
                    mSupportFragmentManager
                        .beginTransaction()
                        .add(containerViewId, fragment)
                        .commit()
                else
                    mSupportFragmentManager
                        .beginTransaction()
                        .hide(mCurrentShowingFragment!!)
                        .add(containerViewId, fragment)
                        .commit()
            else
                mSupportFragmentManager
                    .beginTransaction()
                    .hide(mCurrentShowingFragment!!)
                    .show(fragment)
                    .commit()

            mCurrentShowingFragment = fragment
        }
    }
}