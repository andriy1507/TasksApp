package com.spaceapps.tasks.local.di.module

import android.content.Context
import androidx.room.Room
import com.spaceapps.tasks.local.SpaceAppsTaskDb
import dagger.Module
import dagger.Provides

@Module
class RoomModule {

    @Provides
    fun provideDatabase(context: Context): SpaceAppsTaskDb {
        return Room.databaseBuilder(
            context,
            SpaceAppsTaskDb::class.java,
            "spaceApps Tasks DB"
        ).build()
    }
}