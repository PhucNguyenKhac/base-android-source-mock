package com.example.external.test

import android.util.Log
import com.example.domain.di.DefaultDispatcher
import com.example.domain.result.Result
import com.example.domain.result.data
import com.example.domain.test.ChannelRepository
import com.example.domain.test.ChannelResponseDomain
import com.example.external.provider.BaseProvider
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class ChannelProvider @Inject constructor(
    private val channelService: ChannelService,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ChannelRepository, BaseProvider(defaultDispatcher) {

    override suspend fun getInfo(): Flow<Result<ChannelResponseDomain>> {
        return flow {
            emit(safeApiCall {
                val response = channelService.getInfo("snippet", "UCkXmLjEr95LVtGuIm3l2dPg","AIzaSyBV7RM1z-3NeAxn8-jZktdYqLT5TqrbNXI")
//                val response = channelService.getInfo()
//                Log.e("api response", response.toString())
                response
            })
        }.map {
            when (it) {
                is Result.Success -> {
                    Result.Success(
                        ChannelResponseDomain(
                            items = it.data.mapChannelEntityListToDomain(it.data.items)
                        )
                    )
                }

                else -> Result.Success(ChannelResponseDomain(it?.data!!.mapChannelEntityListToDomain(
                    it.data!!.items)))
            }
        }
    }



}