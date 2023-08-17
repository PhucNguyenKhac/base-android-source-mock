package com.example.android.ui.test

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.android.R
import com.example.android.databinding.ActivityChannelBinding
import com.example.android.ui.activity.BaseActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class ChannelActivity : BaseActivity(), HasAndroidInjector {
    private lateinit var binding: ActivityChannelBinding

    @Inject
    internal lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override val toolBar: Toolbar?
        get() = null

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ChannelViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityChannelBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.isLoading.observe(this) {
            if (it) {
                loading()
            } else {
                dismiss()
            }
        }

        viewModel.channelResult.observe(this) {
            if (it) {
                Toast.makeText(this@ChannelActivity, "Success", Toast.LENGTH_SHORT).show()
                setUpViews()
            } else {
                Toast.makeText(this@ChannelActivity, "Failed", Toast.LENGTH_SHORT).show()
            }
        }

        getData()

    }


    private fun setUpViews() {
        viewModel.channel.observe(this) {
            it.items?.forEach { channel ->
                binding.tvChannelName.text = channel.snippet?.title
                binding.tvChannelDescription.text = channel.snippet?.description
                Glide.with(this).load(channel.snippet?.thumbnails?.high?.url)
                    .error(R.drawable.image_not_available)
                    .into(binding.imageLogo)
//                Glide.with(this).load(channel.branding?.image?.bannerUrl)
//                    .into(binding.imageChannel)
            }
        }
    }


    private fun getData() {
        val db = FirebaseFirestore.getInstance()
        db.collection("Artist")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("fire", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("fire", "Error getting documents.", exception)
            }
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}