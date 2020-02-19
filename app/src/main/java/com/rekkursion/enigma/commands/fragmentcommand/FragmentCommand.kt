package com.rekkursion.enigma.commands.fragmentcommand

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.rekkursion.enigma.commands.BaseCommand

abstract class FragmentCommand(supportFragmentManager: FragmentManager): BaseCommand {
    protected val mSupportFragmentManager: FragmentManager = supportFragmentManager
    protected var mCurrentShowingFragment: Fragment? = null
}