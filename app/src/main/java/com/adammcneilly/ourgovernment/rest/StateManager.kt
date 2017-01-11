package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.State
import com.adammcneilly.ourgovernment.models.StateList
import retrofit2.Call
import rx.Observable

/**
 * Handles all `State` calls.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
class StateManager : BaseManager() {
    private val stateService: StateService = retrofit.create(StateService::class.java)

    fun getStateIDs(): Observable<StateList> {
        return stateService.getStateIDs()
    }

    fun getState(stateId: String): Call<State> {
        return stateService.getState(stateId)
    }
}