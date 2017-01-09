package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.CandidateList
import retrofit2.Call

/**
 * Makes all Officials calls.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
class OfficialsManager : BaseManager() {
    val officialsService: OfficialsService = retrofit.create(OfficialsService::class.java)

    fun getByZip(zip5: String): Call<CandidateList> {
        return getByZip(zip5, "")
    }

    fun getByZip(zip5: String, zip4: String): Call<CandidateList> {
        return officialsService.getByZip(zip5, zip4)
    }
}