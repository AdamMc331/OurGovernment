package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.CandidateBio
import retrofit2.Call

/**
 * Handles all CandidateBio calls.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
class CandidateBioManager : VSApi() {
    val candidateBioService: CandidateBioService = retrofit.create(CandidateBioService::class.java)

    fun getBio(candidateId: String): Call<CandidateBio> {
        return candidateBioService.getBio(candidateId)
    }
}