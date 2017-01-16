package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.CandidateList
import rx.Observable

/**
 * Makes all Officials calls.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
class OfficialsManager : BaseManager() {
    val officialsService: OfficialsService = retrofit.create(OfficialsService::class.java)

    fun getByZip(zip5: String): Observable<CandidateList> {
        return getByZip(zip5, "")
    }

    fun getByZip(zip5: String, zip4: String): Observable<CandidateList> {
        return officialsService.getByZip(zip5, zip4)
    }

    fun getStatewideOfficials(stateId: String): Observable<CandidateList> {
        return officialsService.getStatewideOfficials(stateId)
    }
}