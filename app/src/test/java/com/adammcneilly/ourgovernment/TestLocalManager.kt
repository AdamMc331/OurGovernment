package com.adammcneilly.ourgovernment

import com.adammcneilly.ourgovernment.models.CandidateList
import com.adammcneilly.ourgovernment.models.CityList
import com.adammcneilly.ourgovernment.models.CountyList
import com.adammcneilly.ourgovernment.models.Error
import com.adammcneilly.ourgovernment.rest.LocalManager
import com.adammcneilly.ourgovernment.rest.MockInterceptor
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CountDownLatch

/**
 * Tests all calls in LocalManager.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
class TestLocalManager {
    val api = LocalManager()
    val candidateListSuccess: CandidateList = api.gson.fromJson(CandidateList().getSuccessJson()[0], CandidateList::class.java)
    val countyListSuccess: CountyList = api.gson.fromJson(CountyList().getSuccessJson()[0], CountyList::class.java)
    val cityListSuccess: CityList = api.gson.fromJson(CityList().getSuccessJson()[0], CityList::class.java)

    @Before
    fun setup() {
        api.registerMockResponse(GET_COUNTIES_PATH, CountyList())
        api.registerMockResponse(GET_CITIES_PATH, CityList())
        api.registerMockResponse(GET_LOCAL_OFFICIALS_PATH, CandidateList())
    }

    @After
    fun teardown() {
        api.setApiMode(MockInterceptor.APIMode.LIVE)
    }

    @Test
    fun testGetCounties() {
        api.setApiMode(MockInterceptor.APIMode.MOCK_SUCCESS)

        val countdown = CountDownLatch(1)
        var countyList: CountyList? = null

        val call = api.getCounties("MI")
        call.enqueue(object : Callback<CountyList> {
            override fun onFailure(call: Call<CountyList>?, t: Throwable?) {
                t?.printStackTrace()
                countdown.countDown()
            }

            override fun onResponse(call: Call<CountyList>?, response: Response<CountyList>?) {
                countyList = response?.body()
                countdown.countDown()
            }
        })
        countdown.await()

        assertNotNull(countyList)
        assertEquals(countyListSuccess, countyList)
    }

    @Test
    fun testGetCities() {
        api.setApiMode(MockInterceptor.APIMode.MOCK_SUCCESS)

        val countdown = CountDownLatch(1)
        var cityList: CityList? = null

        val call = api.getCities("")
        call.enqueue(object : Callback<CityList> {
            override fun onFailure(call: Call<CityList>?, t: Throwable?) {
                t?.printStackTrace()
                countdown.countDown()
            }

            override fun onResponse(call: Call<CityList>?, response: Response<CityList>?) {
                cityList = response?.body()
                countdown.countDown()
            }
        })
        countdown.await()

        assertNotNull(cityList)
        assertEquals(cityListSuccess, cityList)
    }

    @Test
    fun testGetOfficials() {
        api.setApiMode(MockInterceptor.APIMode.MOCK_SUCCESS)

        val countdown = CountDownLatch(1)
        var candidateList: CandidateList? = null

        val call = api.getLocalOfficials("")
        call.enqueue(object : Callback<CandidateList> {
            override fun onFailure(call: Call<CandidateList>?, t: Throwable?) {
                t?.printStackTrace()
                countdown.countDown()
            }

            override fun onResponse(call: Call<CandidateList>?, response: Response<CandidateList>?) {
                candidateList = response?.body()
                countdown.countDown()
            }
        })
        countdown.await()

        assertNotNull(candidateList)
        assertEquals(candidateListSuccess, candidateList)
    }

    companion object {
        val GET_COUNTIES_PATH = "/Local.getCounties"
        val GET_CITIES_PATH = "/Local.getCities"
        val GET_LOCAL_OFFICIALS_PATH = "/Local.getOfficials"
    }
}