package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.CandidateBio
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Handles all `CandidateBio` calls.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
interface CandidateBioService {
    @GET("CandidateBio.getBio")
    fun getBio(@Query("candidateId") candidateId: String): Call<CandidateBio>
}