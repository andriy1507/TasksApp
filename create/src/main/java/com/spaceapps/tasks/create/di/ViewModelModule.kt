package com.spaceapps.tasks.create.di

import androidx.lifecycle.ViewModelProvider
import com.spaceapps.tasks.create.CreateTaskFragment
import com.spaceapps.tasks.create.CreateTaskViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindFactory(factory: CreateTaskViewModel.Factory): ViewModelProvider.Factory

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideViewModel(
            factory: ViewModelProvider.Factory,
            fragment: CreateTaskFragment
        ): CreateTaskViewModel {
            return ViewModelProvider(
                fragment.viewModelStore,
                factory
            ).get(CreateTaskViewModel::class.java)
        }
    }
}