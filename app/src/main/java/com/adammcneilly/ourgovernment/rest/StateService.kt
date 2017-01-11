package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.State
import com.adammcneilly.ourgovernment.models.StateList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Handles all `State` calls.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
interface StateService {
    @GET("State.getStateIDs")
    fun getStateIDs(): Observable<StateList>

    @GET("State.getState")
    fun getState(@Query("stateId") stateId: String): Call<State>
}