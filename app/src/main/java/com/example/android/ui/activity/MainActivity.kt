package com.example.android.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import com.example.android.R
import com.example.android.databinding.ActivityMainBinding

class MainActivity(override val toolBar: Toolbar? = null) : BaseActivity() {

    private var selectedTab = 1

    companion object {
        private const val TAB_HOME = 1
        private const val TAB_LIBRARY = 2
        private const val TAB_HISTORY = 3
        private const val TAB_PROFILE = 4
    }

    private val imgTop by lazy {
        listOf(
            mainBinding.imgTopHome,
            mainBinding.imgTopPlaylist,
            mainBinding.imgTopHistory,
            mainBinding.imgTopProfile
        )
    }

    private val tvBottomBarTab by lazy {
        listOf(
            mainBinding.tvHome,
            mainBinding.tvPlaylist,
            mainBinding.tvHistory,
            mainBinding.tvProfile
        )
    }

    private val imgBottomBar by lazy {
        listOf(
            mainBinding.imgHome,
            mainBinding.imgPlaylist,
            mainBinding.imgHistory,
            mainBinding.imgProfile
        )
    }

    private val drawableResources = listOf(
        R.drawable.ic_home,
        R.drawable.ic_playlist,
        R.drawable.ic_history,
        R.drawable.ic_profile
    )

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        setBottomBarViewListener()

    }

    private fun selectTab(
        tab: Int,
        selectedImageId: Int,
        selectedTextView: TextView
    ) {
        if (selectedTab != tab) {

            imgTop.forEach { it.hide() }

            imgBottomBar.forEachIndexed { index, imageView ->
                imageView.setImageResourceCompat(drawableResources[index])
            }

            tvBottomBarTab.forEach { it.setTextColorRes(R.color.stroke_letter) }

            selectedTextView.setTextColorRes(R.color.green_text)

            val imageViewToHideAndSet = when (tab) {
                TAB_HOME -> mainBinding.imgTopHome to mainBinding.imgHome
                TAB_LIBRARY -> mainBinding.imgTopPlaylist to mainBinding.imgPlaylist
                TAB_HISTORY -> mainBinding.imgTopHistory to mainBinding.imgHistory
                TAB_PROFILE -> mainBinding.imgTopProfile to mainBinding.imgProfile
                else -> null to null
            }

            imageViewToHideAndSet.first?.hide()
            imageViewToHideAndSet.second?.setImageResourceCompat(selectedImageId)

            selectedTab = tab
        }
    }

    private fun setBottomBarViewListener() {
        mainBinding.homeLayout.setOnClickListener {
            selectTab(TAB_HOME, R.drawable.ic_home_selected, mainBinding.tvHome)
            findNavController(R.id.splash_nav_host_fragment).navigate(R.id.homepageFragment)
        }

        mainBinding.playlistLayout.setOnClickListener {
            selectTab(
                TAB_LIBRARY,
                R.drawable.ic_playlist_selected,
                mainBinding.tvPlaylist
            )
            findNavController(R.id.splash_nav_host_fragment).navigate(R.id.playlistFragment)
        }

        mainBinding.historyLayout.setOnClickListener {
            selectTab(
                TAB_HISTORY,
                R.drawable.ic_clock_selected,
                mainBinding.tvHistory
            )
            findNavController(R.id.splash_nav_host_fragment).navigate(R.id.historyFragment)
        }

        mainBinding.profileLayout.setOnClickListener {
            selectTab(
                TAB_PROFILE,
                R.drawable.ic_profile_selected,
                mainBinding.tvProfile
            )
            findNavController(R.id.splash_nav_host_fragment).navigate(R.id.profileFragment)

        }
    }

    private fun View.hide() {
        visibility = View.GONE
    }

    private fun TextView.setTextColorRes(colorRes: Int) {
        setTextColor(context.getColor(colorRes))
    }

    private fun ImageView.setImageResourceCompat(resourceId: Int) {
        setImageResource(resourceId)
    }

}

