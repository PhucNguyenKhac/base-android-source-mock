package com.example.domain.channel

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import com.example.domain.result.Result

class ChannelUseCase @Inject constructor(
    private val channelRepository: ChannelRepository
) {
    suspend operator fun invoke(): Flow<Result<SearchChannelResponseDomain>> =
        channelRepository.searchChannelInfo()
}