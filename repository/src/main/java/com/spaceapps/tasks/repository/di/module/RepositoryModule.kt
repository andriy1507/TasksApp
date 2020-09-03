package com.spaceapps.tasks.repository.di.module

import com.spaceapps.tasks.core.repository.AuthorizationRepository
import com.spaceapps.tasks.core.repository.TasksRepository
import com.spaceapps.tasks.core.repository.UserProfileRepository
import com.spaceapps.tasks.repository.AuthorizationRepositoryImpl
import com.spaceapps.tasks.repository.TasksRepositoryImpl
import com.spaceapps.tasks.repository.UserProfileRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
interface RepositoryModule {

    @Binds
    fun bindTasksRepository(impl: TasksRepositoryImpl): TasksRepository

    @Binds
    fun bindAuthorizationRepository(impl: AuthorizationRepositoryImpl): AuthorizationRepository

    @Binds
    fun bindUserProfileRepository(impl: UserProfileRepositoryImpl): UserProfileRepository
}