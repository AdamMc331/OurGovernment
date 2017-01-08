package com.adammcneilly.ourgovernment

import com.adammcneilly.ourgovernment.models.State
import com.adammcneilly.ourgovernment.models.StateList
import com.adammcneilly.ourgovernment.rest.MockInterceptor
import com.adammcneilly.ourgovernment.rest.StateManager
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Tests all calls in StateManager.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
class TestStateManager {
    val api = StateManager()

    @Before
    fun setup() {
        api.registerMockResponse(GET_STATE_IDS_PATH, StateList())
        api.registerMockResponse(GET_STATE_PATH, State())
    }

    @After
    fun teardown() {
        api.setApiMode(MockInterceptor.APIMode.LIVE)
    }

    @Test
    fun testGetStateIDs() {
        api.setApiMode(MockInterceptor.APIMode.MOCK_SUCCESS)

        val countdown = CountDownLatch(1)
        var baseStateList: StateList? = null

        val call = api.getStateIDs()
        call.enqueue(object : Callback<StateList> {
            override fun onFailure(call: Call<StateList>?, t: Throwable?) {
                t?.printStackTrace()
                countdown.countDown()
            }

            override fun onResponse(call: Call<StateList>?, response: Response<StateList>?) {
                baseStateList = response?.body()
                countdown.countDown()
            }
        })
        countdown.await()

        //TODO: Find a way to convert the XML string to a POJO so that we don't have to hardcode this
        TestCase.assertNotNull(baseStateList)
        TestCase.assertEquals(3, baseStateList!!.list.size)
        TestCase.assertEquals("MI", baseStateList!!.list[0].stateId)
        TestCase.assertEquals("OH", baseStateList!!.list[1].stateId)
        TestCase.assertEquals("FL", baseStateList!!.list[2].stateId)
    }

    @Test
    fun testGetState() {
        api.setApiMode(MockInterceptor.APIMode.MOCK_SUCCESS)

        val countdown = CountDownLatch(1)
        var state: com.adammcneilly.ourgovernment.models.State? = null

        val call = api.getState("MI")
        call.enqueue(object: Callback<com.adammcneilly.ourgovernment.models.State> {
            override fun onResponse(call: Call<com.adammcneilly.ourgovernment.models.State>?, response: Response<com.adammcneilly.ourgovernment.models.State>?) {
                state = response?.body()
                countdown.countDown()
            }

            override fun onFailure(call: Call<com.adammcneilly.ourgovernment.models.State>?, t: Throwable?) {
                t?.printStackTrace()
                countdown.countDown()
            }
        })
        countdown.await(5, TimeUnit.SECONDS)

        //TODO: Find a way to convert the XML string to a POJO so that we don't have to hardcode this
        TestCase.assertNotNull(state)
        TestCase.assertEquals("MI", state!!.details.stateId)
        TestCase.assertEquals("Michigan", state!!.details.name)
        TestCase.assertEquals("Lansing", state!!.details.capital)
        TestCase.assertEquals("Sixth", state!!.details.usCircuit)
    }

    companion object {
        val GET_STATE_IDS_PATH = "/State.getStateIDs"
        val GET_STATE_PATH = "/State.getState"
    }
}