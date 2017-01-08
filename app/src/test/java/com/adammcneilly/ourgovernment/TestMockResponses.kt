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