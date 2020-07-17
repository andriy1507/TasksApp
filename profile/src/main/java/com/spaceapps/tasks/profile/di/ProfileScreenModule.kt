package com.spaceapps.tasks.profile.di

import androidx.lifecycle.ViewModelProvider
import com.spaceapps.tasks.profile.ProfileFragment
import com.spaceapps.tasks.profile.ProfileScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ProfileScreenModule {

    @Binds
    abstract fun bindFactory(impl: ProfileScreenViewModel.Factory): ViewModelProvider.Factory

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideViewModel(
            fragment: ProfileFragment,
            factory: ViewModelProvider.Factory
        ): ProfileScreenViewModel {
            return ViewModelProvider(fragment, factory).get(ProfileScreenViewModel::class.java)
        }
    }
}
