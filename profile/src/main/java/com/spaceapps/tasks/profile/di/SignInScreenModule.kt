package com.spaceapps.tasks.profile.di

import androidx.lifecycle.ViewModelProvider
import com.spaceapps.tasks.profile.SignInFragment
import com.spaceapps.tasks.profile.SignInViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class SignInScreenModule {
    @Binds
    abstract fun bindViewModelFactory(factory: SignInViewModel.Factory): ViewModelProvider.Factory
    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideSignInViewModel(fragment: SignInFragment, factory: ViewModelProvider.Factory):SignInViewModel{
            return ViewModelProvider(fragment, factory).get(SignInViewModel::class.java)
        }

    }

}