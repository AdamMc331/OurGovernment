package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.CandidateList
import com.adammcneilly.ourgovernment.models.CityList
import com.adammcneilly.ourgovernment.models.CountyList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Handles all `Local` calls.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
interface LocalService {
    @GET("Local.getCounties")
    fun getCounties(@Query("stateId") stateId: String): Call<CountyList>

    @GET("Local.getCities")
    fun getCities(@Query("stateId") stateId: String): Call<CityList>

    @GET("Local.getOfficials")
    fun getLocalOfficials(@Query("localId") localId: String): Call<CandidateList>
}