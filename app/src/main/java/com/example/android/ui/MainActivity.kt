package com.example.android.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.android.ui.theme.MainActivityTheme
import com.example.android.viewmodel.WellnessViewModel
import com.example.android.viewmodel.getWellnessTasks
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    internal lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val list = remember { getWellnessTasks().toMutableStateList() }
                    Column() {
                        StatefulCounter()
                        WellnessTasksList(
                            list = list,
                            onCloseTask = { task -> list.remove(task) }
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun StatefulCounter(modifier: Modifier = Modifier) {
        var count by remember { mutableStateOf(0) }
        StatelessCounter(
            count = count,
            onIncrement = { count++ },
            modifier = modifier
        )
    }

    @Composable
    fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
        Column(modifier = modifier.padding(16.dp)) {
            if (count > 0) {
                Text("You've had $count glasses.")
            }
            Button(
                onClick = onIncrement,
                enabled = count < 10,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Add one")
            }
        }
    }
    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}