package com.spaceapps.tasks.core

import android.content.Context
import com.spaceapps.tasks.core.di.ApplicationProvider

interface App {

    fun getProvider(): ApplicationProvider

    fun getContext(): Context

}