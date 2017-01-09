package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.CandidateList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Handles all Officials calls.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
interface OfficialsService {

    @GET("Officials.getByZip")
    fun getByZip(@Query("zip5") zip5: String, @Query("zip4") zip4: String): Call<CandidateList>
}