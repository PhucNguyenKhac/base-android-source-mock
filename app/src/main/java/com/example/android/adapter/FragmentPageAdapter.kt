package com.example.android.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.android.ui.GetStartedFragment
import com.example.android.ui.bottom_nav_fragment.ArtistFragment
import com.example.android.ui.login.LoginFragment
import com.example.android.ui.login.RegisterFragment

class FragmentPageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ArtistFragment()
            1 -> RegisterFragment()
            2 -> GetStartedFragment()
            else -> LoginFragment()
        }
    }
}