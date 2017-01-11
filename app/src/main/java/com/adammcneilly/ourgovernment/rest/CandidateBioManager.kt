package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.CandidateBio
import rx.Observable

/**
 * Handles all CandidateBio calls.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
class CandidateBioManager : BaseManager() {
    val candidateBioService: CandidateBioService = retrofit.create(CandidateBioService::class.java)

    fun getBio(candidateId: String): Observable<CandidateBio> {
        return candidateBioService.getBio(candidateId)
    }
}