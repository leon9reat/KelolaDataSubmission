package com.medialink.keloladatasubmission.ui.main

import androidx.fragment.app.Fragment

interface IMainActivity {
    fun getCount(): Int

    fun getFragment(position: Int): Fragment

    fun getPageTitle(position: Int): CharSequence
}