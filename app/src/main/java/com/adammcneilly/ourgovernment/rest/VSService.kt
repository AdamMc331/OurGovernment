package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.BaseStateList
import com.adammcneilly.ourgovernment.models.CityList
import com.adammcneilly.ourgovernment.models.CountyList
import com.adammcneilly.ourgovernment.models.State
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface into the VoteSmart API.

 * Created by adam.mcneilly on 12/26/16.
 */
interface VSService {
    @GET("State.getStateIDs")
    fun getStateIDs(): Call<BaseStateList>

    @GET("State.getState")
    fun getState(@Query("stateId") stateId: String): Call<State>

    @GET("Local.getCounties")
    fun getCounties(@Query("stateId") stateId: String): Call<CountyList>

    @GET("Local.getCities")
    fun getCities(@Query("stateId") stateId: String): Call<CityList>
}
