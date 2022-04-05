package com.example.springprojectstationbuild

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.springprojectstationbuild.service.ComponentService
import com.example.springprojectstationbuild.service.ComponentServiceStub
import com.example.springprojectstationbuild.dto.ComputerComponent
import kotlinx.coroutines.test.runTest

import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var componentService : ComponentServiceStub
    //Stub until we configure Firebase
    //var allComponents : List<ComputerComponent>? = ArrayList<ComputerComponent>()
    var allComponents : Set<ComputerComponent>? = HashSet<ComputerComponent>()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `List is not empty`() = runTest {
        givenComponentServiceIsInitialized()
        whenComponentDataAreReadAndParsed()
        thenTheComponentCollectionIsNotEmpty()
    }


    private fun givenComponentServiceIsInitialized(){
        componentService = ComponentServiceStub()
    }

    private fun whenComponentDataAreReadAndParsed() {
        allComponents = componentService.fetchComputerComponent()
    }

    private fun thenTheComponentCollectionIsNotEmpty() {

        assertNotNull(allComponents)
        assertTrue(allComponents!!.isNotEmpty())
        var containsCPU = false
        allComponents!!.forEach{
            if (it.name.equals("CPU")) {
                containsCPU = true
            }
        }
            assertTrue(containsCPU)
        }
    }
