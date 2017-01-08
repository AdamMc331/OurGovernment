package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.State
import com.adammcneilly.ourgovernment.models.StateList
import retrofit2.Call

/**
 * Handles all `State` calls.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
class StateManager : VSApi() {
    private val stateService: StateService = retrofit.create(StateService::class.java)

    fun getStateIDs(): Call<StateList> {
        return stateService.getStateIDs()
    }

    fun getState(stateId: String): Call<State> {
        return stateService.getState(stateId)
    }
}