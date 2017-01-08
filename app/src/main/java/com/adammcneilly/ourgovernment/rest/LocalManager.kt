package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.CandidateList
import com.adammcneilly.ourgovernment.models.CityList
import com.adammcneilly.ourgovernment.models.CountyList
import retrofit2.Call

/**
 * Manages all of the Local calls.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
class LocalManager : VSApi() {
    val localService: LocalService = retrofit.create(LocalService::class.java)

    fun getCounties(stateId: String): Call<CountyList> {
        return localService.getCounties(stateId)
    }

    fun getCities(stateId: String): Call<CityList> {
        return localService.getCities(stateId)
    }

    fun getLocalOfficials(localId: String): Call<CandidateList> {
        return localService.getLocalOfficials(localId)
    }
}