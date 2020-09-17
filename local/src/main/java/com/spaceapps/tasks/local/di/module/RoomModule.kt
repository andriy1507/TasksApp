package com.spaceapps.tasks.local.di.module

import android.content.Context
import androidx.room.Room
import com.spaceapps.tasks.local.SpaceAppsTaskDb
import com.spaceapps.tasks.local.migration.MigrationFrom1To2
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.asExecutor

@Module
@InstallIn(ApplicationComponent::class)
class RoomModule {

    @Provides
    fun provideDatabase(context: Context): SpaceAppsTaskDb {
        return Room.databaseBuilder(
            context,
            SpaceAppsTaskDb::class.java,
            "spaceApps Tasks DB"
        )
            .setQueryExecutor(IO.asExecutor())
            .setTransactionExecutor(IO.asExecutor())
            .addMigrations(MigrationFrom1To2())
            .build()
    }
}