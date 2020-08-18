package com.spaceapps.tasks.account.di.module

import android.content.Context
import com.spaceapps.tasks.account.SpaceAppsAuthenticator
import com.spaceapps.tasks.core.repository.AuthorizationRepository
import dagger.Module
import dagger.Provides

@Module
class AuthenticatorModule {

    @Provides
    fun provideAuthenticator(
        context: Context,
        authorizationRepository: AuthorizationRepository
    ): SpaceAppsAuthenticator {
        return SpaceAppsAuthenticator(context, authorizationRepository)
    }
}