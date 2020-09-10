package com.spaceapps.tasks.local

import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.spaceapps.tasks.local.migration.MigrationFrom1To2
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MigrationTest {

    @get:Rule
    var helper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        SpaceAppsTaskDb::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    @Throws(IOException::class)
    fun migrate1To2() {
        helper.createDatabase(TEST_DB, 1)
        helper.runMigrationsAndValidate(TEST_DB, 2, true, MigrationFrom1To2())
    }

    companion object {
        private const val TEST_DB = "migration-test"
    }
}