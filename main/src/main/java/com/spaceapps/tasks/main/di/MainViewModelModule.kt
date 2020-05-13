package com.spaceapps.tasks.main.di

import androidx.lifecycle.ViewModelProvider
import com.spaceapps.tasks.main.MainFragment
import com.spaceapps.tasks.main.MainScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class MainViewModelModule {

    @Binds
    abstract fun bindFactory(impl: MainScreenViewModel.Factory): ViewModelProvider.Factory

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideViewModel(
            factory: ViewModelProvider.Factory,
            fragment: MainFragment
        ): MainScreenViewModel {
            return ViewModelProvider(
                fragment.viewModelStore,
                factory
            ).get(MainScreenViewModel::class.java)
        }
    }
}