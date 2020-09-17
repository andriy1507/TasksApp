package com.spaceapps.tasks.account.di.module

import android.content.Context
import com.spaceapps.tasks.account.SpaceAppsAuthenticator
import com.spaceapps.tasks.core.repository.AuthorizationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.scopes.ServiceScoped

@Module
@InstallIn(ServiceComponent::class)
class AuthenticatorModule {

    @Provides
    @ServiceScoped
    fun provideAuthenticator(
        context: Context,
        authorizationRepository: AuthorizationRepository
    ): SpaceAppsAuthenticator {
        return SpaceAppsAuthenticator(context, authorizationRepository)
    }
}