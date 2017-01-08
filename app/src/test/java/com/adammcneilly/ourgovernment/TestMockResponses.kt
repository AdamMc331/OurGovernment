package com.adammcneilly.ourgovernment

import com.adammcneilly.ourgovernment.models.*
import com.adammcneilly.ourgovernment.rest.MockInterceptor
import com.adammcneilly.ourgovernment.rest.VSApi
import junit.framework.TestCase.*
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
        api.registerMockResponse(GET_STATE_IDS_PATH, StateList())
        api.registerMockResponse(GET_STATE_PATH, State())
        api.registerMockResponse(GET_COUNTIES_PATH, CountyList())
        api.registerMockResponse(GET_CITIES_PATH, CityList())
        api.registerMockResponse(GET_LOCAL_OFFICIALS_PATH, CandidateList())
        api.registerMockResponse(GET_BIO_PATH, CandidateBio())
    }

    @After
    fun teardown() {
        api.setApiMode(MockInterceptor.APIMode.LIVE)
    }

    //region Local
    @Test
    fun testGetCounties() {
        api.setApiMode(MockInterceptor.APIMode.MOCK_SUCCESS)

        val countdown = CountDownLatch(1)
        var countyList: CountyList? = null

        val call = api.getCounties("MI")
        call.enqueue(object : Callback<CountyList>{
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
        assertNotNull(countyList)
        assertEquals(3, countyList!!.list.size)
        assertEquals("Alcona County", countyList!!.list[0].name)
        assertEquals("Alger County", countyList!!.list[1].name)
        assertEquals("Allegan County", countyList!!.list[2].name)
    }

    @Test
    fun testGetCountiesError() {
        api.setApiMode(MockInterceptor.APIMode.MOCK_ERROR)

        val countdown = CountDownLatch(1)
        var error: Error? = null

        val call = api.getCounties("MI")
        call.enqueue(object : Callback<CountyList>{
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
        assertNotNull(error)
        assertEquals("This probably shouldn't have happened.", error!!.errorMessage)
    }

    @Test
    fun testGetCities() {
        api.setApiMode(MockInterceptor.APIMode.MOCK_SUCCESS)

        val countdown = CountDownLatch(1)
        var cityList: CityList? = null

        val call = api.getCities("")
        call.enqueue(object : Callback<CityList>{
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
        assertEquals(3, cityList!!.list.size)
        assertEquals("Allen Park", cityList!!.list[0].name)
        assertEquals("Ann Arbor", cityList!!.list[1].name)
        assertEquals("Battle Creek", cityList!!.list[2].name)
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
        assertEquals(1, candidateList!!.list.size)
        assertEquals("James", candidateList!!.list[0].firstName)
        assertEquals("R.", candidateList!!.list[0].middleName)
        assertEquals("Fouts", candidateList!!.list[0].lastName)
    }
    //endregion

    //region Candidate
    @Test
    fun testGetBio() {
        api.setApiMode(MockInterceptor.APIMode.MOCK_SUCCESS)

        val countdown = CountDownLatch(1)
        var candidateBio: CandidateBio? = null

        val call = api.getBio("")
        call.enqueue(object : Callback<CandidateBio>{
            override fun onResponse(call: Call<CandidateBio>?, response: Response<CandidateBio>?) {
                candidateBio = response?.body()
                countdown.countDown()
            }

            override fun onFailure(call: Call<CandidateBio>?, t: Throwable?) {
                t?.printStackTrace()
                countdown.countDown()
            }
        })
        countdown.await()

        assertNotNull(candidateBio)
        assertEquals("James", candidateBio!!.candidate.firstName)
        assertEquals("R.", candidateBio!!.candidate.middleName)
        assertEquals("Fouts", candidateBio!!.candidate.lastName)
        assertEquals("Mayor", candidateBio!!.office.name)
        assertTrue(candidateBio!!.election.ballotName.isEmpty())
    }
    //endregion

    companion object {
        val GET_STATE_IDS_PATH = "/State.getStateIDs"
        val GET_STATE_PATH = "/State.getState"
        val GET_COUNTIES_PATH = "/Local.getCounties"
        val GET_CITIES_PATH = "/Local.getCities"
        val GET_LOCAL_OFFICIALS_PATH = "/Local.getOfficials"
        val GET_BIO_PATH = "/CandidateBio.getBio"
    }
}