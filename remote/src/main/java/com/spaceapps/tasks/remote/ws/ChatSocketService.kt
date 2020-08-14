package com.spaceapps.tasks.remote.ws

import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.flow.Flow

interface ChatSocketService {
    @Send
    fun sendChatMessage(msg: String)

    @Receive
    fun listenChatMessages(): Flow<String>
}