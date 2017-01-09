package com.adammcneilly.ourgovernment

import com.adammcneilly.ourgovernment.models.CandidateList
import com.adammcneilly.ourgovernment.rest.MockInterceptor
import com.adammcneilly.ourgovernment.rest.OfficialsManager
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
 * Tests all calls in the Officials manager
 *
 * Created by adam.mcneilly on 1/8/17.
 */
class TestOfficialsManager {
    val api = OfficialsManager()
    val candidateListSuccess: CandidateList = api.gson.fromJson(CandidateList().getSuccessJson()[0], CandidateList::class.java)

    @Before
    fun setup() {
        api.registerMockResponse(GET_BY_ZIP_PATH, CandidateList())
    }

    @After
    fun teardown() {
        api.setApiMode(MockInterceptor.APIMode.LIVE)
    }

    @Test
    fun testGetByZip() {
        api.setApiMode(MockInterceptor.APIMode.MOCK_SUCCESS)

        val countdown = CountDownLatch(1)
        var candidateList: CandidateList? = null

        val call = api.getByZip("test")
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
        val GET_BY_ZIP_PATH = "/Officials.getByZip"
    }
}