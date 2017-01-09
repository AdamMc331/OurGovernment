package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.OfficeTypeList
import retrofit2.Call
import retrofit2.http.GET

/**
 * Handles all office calls.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
interface OfficeService {
    @GET("Office.getTypes")
    fun getTypes(): Call<OfficeTypeList>
}