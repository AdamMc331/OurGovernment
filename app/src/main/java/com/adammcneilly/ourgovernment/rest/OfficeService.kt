package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.OfficeBranchList
import com.adammcneilly.ourgovernment.models.OfficeLevelList
import com.adammcneilly.ourgovernment.models.OfficeTypeList
import retrofit2.http.GET
import rx.Observable

/**
 * Handles all office calls.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
interface OfficeService {
    @GET("Office.getTypes")
    fun getTypes(): Observable<OfficeTypeList>

    @GET("Office.getBranches")
    fun getBranches(): Observable<OfficeBranchList>

    @GET("Office.getLevels")
    fun getLevels(): Observable<OfficeLevelList>
}