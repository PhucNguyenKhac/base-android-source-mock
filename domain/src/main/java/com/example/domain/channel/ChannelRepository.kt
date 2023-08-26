package com.example.domain.channel

import kotlinx.coroutines.flow.Flow
import com.example.domain.result.Result
interface ChannelRepository {
    suspend fun searchChannelInfo(): Flow<Result<SearchChannelResponseDomain>>
}
