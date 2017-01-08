package com.adammcneilly.ourgovernment

import com.adammcneilly.ourgovernment.models.CandidateList
import com.adammcneilly.ourgovernment.models.CityList
import com.adammcneilly.ourgovernment.models.CountyList
import com.adammcneilly.ourgovernment.models.Error
import com.adammcneilly.ourgovernment.rest.LocalManager
import com.adammcneilly.ourgovernment.rest.MockInterceptor
import junit.framework.TestCase
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

        //TODO: Find a way to convert the XML string to a POJO so that we don't have to hardcode this
        TestCase.assertNotNull(countyList)
        TestCase.assertEquals(3, countyList!!.list.size)
        TestCase.assertEquals("Alcona County", countyList!!.list[0].name)
        TestCase.assertEquals("Alger County", countyList!!.list[1].name)
        TestCase.assertEquals("Allegan County", countyList!!.list[2].name)
    }

    @Test
    fun testGetCountiesError() {
        api.setApiMode(MockInterceptor.APIMode.MOCK_ERROR)

        val countdown = CountDownLatch(1)
        var error: Error? = null

        val call = api.getCounties("MI")
        call.enqueue(object : Callback<CountyList> {
            override fun onFailure(call: Call<CountyList>?, t: Throwable?) {
                t?.printStackTrace()
                countdown.countDown()
            }

            override fun onResponse(call: Call<CountyList>?, response: Response<CountyList>?) {
                // We're assuming error body
                error = Error.parseError(response)
                countdown.countDown()
            }
        })
        countdown.await()

        //TODO: Find a way to convert the XML string to a POJO so that we don't have to hardcode this
        TestCase.assertNotNull(error)
        TestCase.assertEquals("This probably shouldn't have happened.", error!!.errorMessage)
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

        TestCase.assertNotNull(cityList)
        TestCase.assertEquals(3, cityList!!.list.size)
        TestCase.assertEquals("Allen Park", cityList!!.list[0].name)
        TestCase.assertEquals("Ann Arbor", cityList!!.list[1].name)
        TestCase.assertEquals("Battle Creek", cityList!!.list[2].name)
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

        TestCase.assertNotNull(candidateList)
        TestCase.assertEquals(1, candidateList!!.list.size)
        TestCase.assertEquals("James", candidateList!!.list[0].firstName)
        TestCase.assertEquals("R.", candidateList!!.list[0].middleName)
        TestCase.assertEquals("Fouts", candidateList!!.list[0].lastName)
    }

    companion object {
        val GET_COUNTIES_PATH = "/Local.getCounties"
        val GET_CITIES_PATH = "/Local.getCities"
        val GET_LOCAL_OFFICIALS_PATH = "/Local.getOfficials"
    }
}