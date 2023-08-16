package com.example.android.ui.bottom_nav_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.databinding.FragmentProfileBinding
import com.example.android.ui.fragment.BaseFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class ProfileFragment : BaseFragment(), HasAndroidInjector {

    @Inject
    internal lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private val profileBinding: FragmentProfileBinding by lazy {
        FragmentProfileBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        displayListMostPlayedSong()
        // Inflate the layout for this fragment
        return profileBinding.root
    }

    private fun displayListMostPlayedSong() {
        val layout = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        profileBinding.recyclerViewMps.layoutManager = layout
//        val adapter = SongAdapter()
//        adapter.submitList(getListMPS())
//        profileBinding.recyclerViewMps.adapter = adapter
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}







