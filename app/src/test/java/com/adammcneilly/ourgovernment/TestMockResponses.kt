package com.adammcneilly.ourgovernment

import com.adammcneilly.ourgovernment.models.BaseStateList
import com.adammcneilly.ourgovernment.models.CountyList
import com.adammcneilly.ourgovernment.models.State
import com.adammcneilly.ourgovernment.rest.MockInterceptor
import com.adammcneilly.ourgovernment.rest.VSApi
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Runs tests to ensure our mocked responses work accordingly.
 *
 * Created by adam.mcneilly on 12/30/16.
 */
class TestMockResponses {

    val api = VSApi()

    @Before
    fun setup() {
        api.registerMockResponse(GET_STATE_IDS_PATH, BaseStateList())
        api.registerMockResponse(GET_STATE_PATH, State())
        api.registerMockResponse(GET_COUNTIES_PATH, CountyList())
    }

    @After
    fun teardown() {
        api.setApiMode(MockInterceptor.APIMode.LIVE)
    }

    @Test
    fun testGetStateIDs() {
        api.setApiMode(MockInterceptor.APIMode.MOCK_SUCCESS)

        val countdown = CountDownLatch(1)
        var baseStateList: BaseStateList? = null

        val call = api.getStateIDs()
        call.enqueue(object : Callback<BaseStateList> {
            override fun onFailure(call: Call<BaseStateList>?, t: Throwable?) {
                countdown.countDown()
            }

            override fun onResponse(call: Call<BaseStateList>?, response: Response<BaseStateList>?) {
                baseStateList = response!!.body()
                countdown.countDown()
            }
        })
        countdown.await()

        //TODO: Find a way to convert the XML string to a POJO so that we don't have to hardcode this
        assertNotNull(baseStateList)
        assertEquals(3, baseStateList!!.list.size)
        assertEquals("MI", baseStateList!!.list[0].stateId)
        assertEquals("OH", baseStateList!!.list[1].stateId)
        assertEquals("FL", baseStateList!!.list[2].stateId)
    }

    @Test
    fun testGetState() {
        api.setApiMode(MockInterceptor.APIMode.MOCK_SUCCESS)

        val countdown = CountDownLatch(1)
        var state: State? = null

        val call = api.getState("MI")
        call.enqueue(object: Callback<State> {
            override fun onResponse(call: Call<State>?, response: Response<State>?) {
                state = response!!.body()
                countdown.countDown()
            }

            override fun onFailure(call: Call<State>?, t: Throwable?) {
                t?.printStackTrace()
                countdown.countDown()
            }
        })
        countdown.await(5, TimeUnit.SECONDS)

        //TODO: Find a way to convert the XML string to a POJO so that we don't have to hardcode this
        assertNotNull(state)
        assertEquals("MI", state!!.details.stateId)
        assertEquals("Michigan", state!!.details.name)
        assertEquals("Lansing", state!!.details.capital)
        assertEquals("Sixth", state!!.details.usCircuit)
    }

    @Test
    fun testGetCounties() {
        api.setApiMode(MockInterceptor.APIMode.MOCK_SUCCESS)

        val countdown = CountDownLatch(1)
        var countyList: CountyList? = null

        val call = api.getCounties("MI")
        call.enqueue(object : Callback<CountyList>{
            override fun onFailure(call: Call<CountyList>?, t: Throwable?) {
                countdown.countDown()
            }

            override fun onResponse(call: Call<CountyList>?, response: Response<CountyList>?) {
                countyList = response!!.body()
                countdown.countDown()
            }
        })
        countdown.await()

        //TODO: Find a way to convert the XML string to a POJO so that we don't have to hardcode this
        assertNotNull(countyList)
        assertEquals(3, countyList!!.list.size)
        assertEquals("Alcona County", countyList!!.list[0].name)
        assertEquals("Alger County", countyList!!.list[1].name)
        assertEquals("Allegan County", countyList!!.list[2].name)
    }

    companion object {
        val GET_STATE_IDS_PATH = "/State.getStateIDs"
        val GET_STATE_PATH = "/State.getState"
        val GET_COUNTIES_PATH = "/Local.getCounties"
    }
}