package com.adammcneilly.ourgovernment

import com.adammcneilly.ourgovernment.models.CandidateBio
import com.adammcneilly.ourgovernment.rest.CandidateBioManager
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
 * Tests all calls for CandidateBioManager.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
class TestCandidateBioManager {

    val api = CandidateBioManager()
    val candidateBioSuccess: CandidateBio = api.gson.fromJson(CandidateBio().getSuccessJson()[0], CandidateBio::class.java)

    @Before
    fun setup() {
        api.registerMockResponse(GET_BIO_PATH, CandidateBio())
    }

    @After
    fun teardown() {
        api.setApiMode(MockInterceptor.APIMode.LIVE)
    }

    @Test
    fun testGetBio() {
        api.setApiMode(MockInterceptor.APIMode.MOCK_SUCCESS)

        val countdown = CountDownLatch(1)
        var candidateBio: CandidateBio? = null

        val call = api.getBio("")
        call.enqueue(object : Callback<CandidateBio> {
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
        assertEquals(candidateBioSuccess, candidateBio)
    }

    companion object {
        val GET_BIO_PATH = "/CandidateBio.getBio"
    }
}