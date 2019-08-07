package ru.timekeeper

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented bottom_navigation_item_background_colors, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under bottom_navigation_item_background_colors.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("ru.timekeeper", appContext.packageName)
    }
}
