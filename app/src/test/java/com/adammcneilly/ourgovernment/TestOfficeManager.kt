package com.adammcneilly.ourgovernment

import com.adammcneilly.ourgovernment.models.OfficeTypeList
import com.adammcneilly.ourgovernment.rest.MockInterceptor
import com.adammcneilly.ourgovernment.rest.OfficeManager
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
 * Tests calls for the OfficeManager.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
class TestOfficeManager {

    val api = OfficeManager()
    val officeTypeListSuccess: OfficeTypeList = api.gson.fromJson(OfficeTypeList().getSuccessJson()[0], OfficeTypeList::class.java)

    @Before
    fun setup() {
        api.registerMockResponse(GET_TYPES_PATH, OfficeTypeList())
    }

    @After
    fun teardown() {
        api.setApiMode(MockInterceptor.APIMode.LIVE)
    }

    @Test
    fun testGetTypes() {
        api.setApiMode(MockInterceptor.APIMode.MOCK_SUCCESS)

        val countdown = CountDownLatch(1)
        var officeTypeList: OfficeTypeList? = null

        val call = api.getTypes()
        call.enqueue(object : Callback<OfficeTypeList> {
            override fun onFailure(call: Call<OfficeTypeList>?, t: Throwable?) {
                t?.printStackTrace()
                countdown.countDown()
            }

            override fun onResponse(call: Call<OfficeTypeList>?, response: Response<OfficeTypeList>?) {
                officeTypeList = response?.body()
                countdown.countDown()
            }
        })
        countdown.await()

        assertNotNull(officeTypeList)
        assertEquals(officeTypeListSuccess, officeTypeList)
    }

    companion object {
        val GET_TYPES_PATH = "/Office.getTypes"
    }
}