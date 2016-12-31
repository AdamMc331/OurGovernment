package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface into the VoteSmart API.

 * Created by adam.mcneilly on 12/26/16.
 */
interface VSService {
    //region State
    @GET("State.getStateIDs")
    fun getStateIDs(): Call<BaseStateList>

    @GET("State.getState")
    fun getState(@Query("stateId") stateId: String): Call<State>
    //endregion

    //region Local
    @GET("Local.getCounties")
    fun getCounties(@Query("stateId") stateId: String): Call<CountyList>

    @GET("Local.getCities")
    fun getCities(@Query("stateId") stateId: String): Call<CityList>

    @GET("Local.getOfficials")
    fun getLocalOfficials(@Query("localId") localId: String): Call<CandidateList>
    //endregion
}
