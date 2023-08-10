package com.example.android.ui.test

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import javax.inject.Inject
import com.example.domain.result.Result
import com.example.domain.test.ChannelResponseDomain
import com.example.domain.test.ChannelUseCase
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ChannelViewModel @Inject constructor(
    private val channelUseCase: ChannelUseCase
) : ViewModel() {
    private val mutableIsLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = mutableIsLoading

    private val mutableChannelResult = MutableLiveData<Boolean>()
    val channelResult: LiveData<Boolean>
        get() = mutableChannelResult

    private val _channel = MutableLiveData<ChannelResponseDomain>()
    val channel = _channel

    private fun startLoading() {
        mutableIsLoading.postValue(true)
    }

    private fun stopLoading() {
        mutableIsLoading.postValue(false)
    }

    init {
        getInfo()
    }

    private fun getInfo() {
        viewModelScope.launch {
            channelUseCase.invoke()
                .onStart {
                    startLoading()
                }.collect {
                    stopLoading()
                    when (it) {
                        is Result.Success -> {
                            mutableChannelResult.value = true
                            _channel.value = it.data
                        }
                        else -> mutableChannelResult.value = false
                    }
                }
        }
    }
}