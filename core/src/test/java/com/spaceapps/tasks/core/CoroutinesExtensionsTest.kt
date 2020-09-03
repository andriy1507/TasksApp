package com.spaceapps.tasks.core

import com.spaceapps.tasks.core.extensions.safeAsync
import com.spaceapps.tasks.core.model.Status
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class CoroutinesExtensionsTest {

    private val mainThreadSurrogate = newSingleThreadContext("Background Thread")

    @Before
    fun before(){
        Dispatchers.setMain(mainThreadSurrogate)
    }


    @Test
    fun testSuccess(){
        CoroutineScope(Dispatchers.Main).launch{
            val status = safeAsync { return@safeAsync Any() }
            assert(status is Status.Success)
        }
    }

    @Test
    fun testError(){
        CoroutineScope(Dispatchers.Main).launch {
            val status = safeAsync {
                throw Exception("TestException")
            }
            assert(status is Status.Error)
        }
    }

    @After
    fun after(){
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}