package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.CandidateList
import com.adammcneilly.ourgovernment.models.CityList
import com.adammcneilly.ourgovernment.models.CountyList
import rx.Observable

/**
 * Manages all of the Local calls.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
class LocalManager : BaseManager() {
    val localService: LocalService = retrofit.create(LocalService::class.java)

    fun getCounties(stateId: String): Observable<CountyList> {
        return localService.getCounties(stateId)
    }

    fun getCities(stateId: String): Observable<CityList> {
        return localService.getCities(stateId)
    }

    fun getLocalOfficials(localId: String): Observable<CandidateList> {
        return localService.getLocalOfficials(localId)
    }
}