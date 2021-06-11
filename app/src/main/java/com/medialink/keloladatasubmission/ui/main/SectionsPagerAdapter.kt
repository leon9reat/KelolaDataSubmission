package com.medialink.keloladatasubmission.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SectionsPagerAdapter(private val mCallback: IMainActivity, fm: FragmentManager):
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = mCallback.getCount()

    override fun getItem(position: Int): Fragment =
        mCallback.getFragment(position)

    override fun getPageTitle(position: Int): CharSequence =
        mCallback.getPageTitle(position)
}