package com.example.domain.test

import kotlinx.coroutines.flow.Flow
import com.example.domain.result.Result
interface ChannelRepository {
    suspend fun getInfo(): Flow<Result<ChannelResponseDomain>>
}
