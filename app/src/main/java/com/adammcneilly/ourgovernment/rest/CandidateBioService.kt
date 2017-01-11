package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.CandidateBio
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Handles all `CandidateBio` calls.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
interface CandidateBioService {
    @GET("CandidateBio.getBio")
    fun getBio(@Query("candidateId") candidateId: String): Observable<CandidateBio>
}