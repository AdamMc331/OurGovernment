package com.adammcneilly.ourgovernment

import com.adammcneilly.ourgovernment.models.BranchList
import com.adammcneilly.ourgovernment.models.OfficeLevelList
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
    val branchListSuccess: BranchList = api.gson.fromJson(BranchList().getSuccessJson()[0], BranchList::class.java)
    val levelListSuccess: OfficeLevelList = api.gson.fromJson(OfficeLevelList().getSuccessJson()[0], OfficeLevelList::class.java)

    @Before
    fun setup() {
        api.registerMockResponse(GET_TYPES_PATH, OfficeTypeList())
        api.registerMockResponse(GET_BRANCHES_PATH, BranchList())
        api.registerMockResponse(GET_LEVELS_PATH, OfficeLevelList())
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

    @Test
    fun testGetBranches() {
        api.setApiMode(MockInterceptor.APIMode.MOCK_SUCCESS)

        val countdown = CountDownLatch(1)
        var branchList: BranchList? = null

        val call = api.getBranches()
        call.enqueue(object : Callback<BranchList> {
            override fun onFailure(call: Call<BranchList>?, t: Throwable?) {
                t?.printStackTrace()
                countdown.countDown()
            }

            override fun onResponse(call: Call<BranchList>?, response: Response<BranchList>?) {
                branchList = response?.body()
                countdown.countDown()
            }
        })
        countdown.await()

        assertNotNull(branchList)
        assertEquals(branchListSuccess, branchList)
    }

    @Test
    fun testGetLevels() {
        api.setApiMode(MockInterceptor.APIMode.MOCK_SUCCESS)

        val countdown = CountDownLatch(1)
        var levelList: OfficeLevelList? = null

        val call = api.getLevels()
        call.enqueue(object : Callback<OfficeLevelList> {
            override fun onFailure(call: Call<OfficeLevelList>?, t: Throwable?) {
                t?.printStackTrace()
                countdown.countDown()
            }

            override fun onResponse(call: Call<OfficeLevelList>?, response: Response<OfficeLevelList>?) {
                levelList = response?.body()
                countdown.countDown()
            }
        })
        countdown.await()

        assertNotNull(levelList)
        assertEquals(levelListSuccess, levelList)
    }

    companion object {
        val GET_TYPES_PATH = "/Office.getTypes"
        val GET_BRANCHES_PATH = "/Office.getBranches"
        val GET_LEVELS_PATH = "/Office.getLevels"
    }
}