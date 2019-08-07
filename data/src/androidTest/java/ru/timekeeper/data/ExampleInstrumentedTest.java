package ru.timekeeper.data;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented bottom_navigation_item_background_colors, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under bottom_navigation_item_background_colors.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("ru.timekeeper.data.bottom_navigation_item_background_colors", appContext.getPackageName());
    }
}
