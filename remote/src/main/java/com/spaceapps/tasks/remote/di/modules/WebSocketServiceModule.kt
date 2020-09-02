package com.spaceapps.tasks.remote.di.modules

import com.spaceapps.tasks.remote.ws.ChatSocketService
import com.tinder.scarlet.Scarlet
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class WebSocketServiceModule {

    @Provides
    fun provideChatSocketService(scarlet: Scarlet): ChatSocketService {
        return scarlet.create(ChatSocketService::class.java)
    }
}