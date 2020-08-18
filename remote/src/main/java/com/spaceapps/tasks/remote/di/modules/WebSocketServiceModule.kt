package com.spaceapps.tasks.remote.di.modules

import com.spaceapps.tasks.remote.ws.ChatSocketService
import com.tinder.scarlet.Scarlet
import dagger.Module
import dagger.Provides

@Module
class WebSocketServiceModule {

    @Provides
    fun provideChatSocketService(scarlet: Scarlet): ChatSocketService {
        return scarlet.create(ChatSocketService::class.java)
    }
}