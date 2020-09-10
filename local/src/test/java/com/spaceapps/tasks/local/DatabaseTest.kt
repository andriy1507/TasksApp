package com.spaceapps.tasks.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.spaceapps.tasks.local.model.SubTaskLocal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class DatabaseTest {

    private val mockSubTask = SubTaskLocal(
        text = "Some text",
        done = false,
        taskId = 1
    )

    private lateinit var database: SpaceAppsTaskDb
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun before() {
        database = Room
            .inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                SpaceAppsTaskDb::class.java
            )
            .build()
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @Test
    fun savingDataTest() {
        CoroutineScope(Dispatchers.Main).launch {
            database.getSubTasksDao().insert(mockSubTask)
            val subTasks = database.getSubTasksDao().selectAll()
            subTasks.collect { assert(it.isNotEmpty()) }
        }
    }

    @Test
    fun deletingDataTest() {
        CoroutineScope(Dispatchers.Main).launch {
            with(database.getSubTasksDao()){
                insert(mockSubTask)
                selectAll().collect { assert(it.isNotEmpty()) }
                delete(mockSubTask)
                selectAll().collect { assert(it.isEmpty()) }
            }
        }
    }

    @After
    fun after() {
        database.close()
        Dispatchers.resetMain()
    }
}