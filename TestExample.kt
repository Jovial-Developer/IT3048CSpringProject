package

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class TestExample {
    @Test
    fun AppContent() {
        val ApplicationContent = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("", ApplicationContent.packageName)
    }
}
