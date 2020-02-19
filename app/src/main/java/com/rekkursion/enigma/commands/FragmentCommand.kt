package com.rekkursion.enigma.commands

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

abstract class FragmentCommand(supportFragmentManager: FragmentManager): BaseCommand {
    protected val mSupportFragmentManager: FragmentManager = supportFragmentManager
    protected var mCurrentShowingFragment: Fragment? = null
}