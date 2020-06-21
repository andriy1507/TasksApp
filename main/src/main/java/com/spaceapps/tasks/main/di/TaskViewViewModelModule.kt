package com.spaceapps.tasks.main.di

import androidx.lifecycle.ViewModelProvider
import com.spaceapps.tasks.main.TaskViewBottomSheet
import com.spaceapps.tasks.main.TaskViewViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class TaskViewViewModelModule {

    @Binds
    abstract fun bindFactory(factory: TaskViewViewModel.Factory): ViewModelProvider.Factory

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideViewModel(
            factory: ViewModelProvider.Factory,
            fragment: TaskViewBottomSheet
        ): TaskViewViewModel {
            return ViewModelProvider(
                fragment.viewModelStore,
                factory
            ).get(TaskViewViewModel::class.java)
        }
    }
}