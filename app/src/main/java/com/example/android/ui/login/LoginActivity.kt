package com.example.android.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.android.databinding.LoginActivityBinding
import com.example.android.ui.BaseActivity
import com.example.android.viewmodel.LoginViewModel
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class LoginActivity : BaseActivity(), HasAndroidInjector {
    private lateinit var binding: LoginActivityBinding

    @Inject
    internal lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override val toolBar: Toolbar?
        get() = null

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpViews()
        viewModel.isLoading.observe(this) {
            if (it) {
                loading()
            } else {
                dismiss()
            }
        }

        viewModel.logInResult.observe(this) {
            if (it) {
                Log.d("#PhucNK1 received ", it.toString())
                Toast.makeText(this@LoginActivity, "Success Login", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("#PhucNK1 received ", it.toString())
                Toast.makeText(this@LoginActivity, "Failed Login", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUpViews() {
        binding.signIn.setOnClickListener {
            viewModel.login(binding.username.text.toString(), binding.password.text.toString())
        }
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}