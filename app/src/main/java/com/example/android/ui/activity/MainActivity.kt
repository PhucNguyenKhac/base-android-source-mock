package com.example.android.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.android.R
import com.example.android.databinding.ActivityMainBinding
import com.example.android.ui.bottom_nav_fragment.HomepageFragment
import com.example.android.ui.bottom_nav_fragment.LibraryFragment
import kotlinx.android.synthetic.main.custom_bottom_bar.view.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private var selectedTab = 1

    companion object {
        private const val TAB_HOME = 1
        private const val TAB_LIBRARY = 2
        private const val TAB_HISTORY = 3
        private const val TAB_PROFILE = 4
    }


    private val mainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        replaceFragment(HomepageFragment())

        mainBinding.homeLayout.setOnClickListener {
            selectTab(TAB_HOME, HomepageFragment(), R.drawable.ic_home_selected, mainBinding.tvHome)
        }

        mainBinding.playlistLayout.setOnClickListener {
            selectTab(TAB_LIBRARY, LibraryFragment(), R.drawable.ic_playlist_selected, mainBinding.tvPlaylist)
        }

        mainBinding.historyLayout.setOnClickListener {
            selectTab(TAB_HISTORY, HomepageFragment(), R.drawable.ic_clock_selected, mainBinding.tvHistory)
        }

        mainBinding.profileLayout.setOnClickListener {
            selectTab(TAB_PROFILE, LibraryFragment(), R.drawable.ic_profile_selected, mainBinding.tvProfile)
        }


    }

    private fun selectTab(tab: Int, fragment: Fragment, selectedImageId: Int, selectedTextView: TextView) {
        if (selectedTab != tab) {
            replaceFragment(fragment)

            mainBinding.imgTopHome.visibility = View.GONE
            mainBinding.imgTopPlaylist.visibility = View.GONE
            mainBinding.imgTopHistory.visibility = View.GONE
            mainBinding.imgTopProfile.visibility = View.GONE

            mainBinding.imgHome.setImageResource(R.drawable.ic_home)
            mainBinding.imgPlaylist.setImageResource(R.drawable.ic_playlist)
            mainBinding.imgHistory.setImageResource(R.drawable.ic_history)
            mainBinding.imgProfile.setImageResource(R.drawable.ic_profile)

            mainBinding.tvHome.setTextColor(resources.getColor(R.color.stroke_letter))
            mainBinding.tvPlaylist.setTextColor(resources.getColor(R.color.stroke_letter))
            mainBinding.tvHistory.setTextColor(resources.getColor(R.color.stroke_letter))
            mainBinding.tvProfile.setTextColor(resources.getColor(R.color.stroke_letter))

            selectedTextView.setTextColor(resources.getColor(R.color.green_text))

            when (tab) {
                TAB_HOME -> {
                    mainBinding.imgTopHome.visibility = View.VISIBLE
                    mainBinding.imgHome.setImageResource(selectedImageId)
                }
                TAB_LIBRARY -> {
                    mainBinding.imgTopPlaylist.visibility = View.VISIBLE
                    mainBinding.imgPlaylist.setImageResource(selectedImageId)
                }
                TAB_HISTORY -> {
                    mainBinding.imgTopHistory.visibility = View.VISIBLE
                    mainBinding.imgHistory.setImageResource(selectedImageId)
                }
                TAB_PROFILE -> {
                    mainBinding.imgTopProfile.visibility = View.VISIBLE
                    mainBinding.imgProfile.setImageResource(selectedImageId)
                }
            }

            selectedTab = tab
        }
    }


    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}

