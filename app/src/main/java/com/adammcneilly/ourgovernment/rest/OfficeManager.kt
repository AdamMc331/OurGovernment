package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.BranchList
import com.adammcneilly.ourgovernment.models.OfficeLevelList
import com.adammcneilly.ourgovernment.models.OfficeTypeList
import retrofit2.Call

/**
 * Handles all of the Office calls.
 * 
 * Created by adam.mcneilly on 1/8/17.
 */
class OfficeManager : BaseManager() {
    private val officeService: OfficeService = retrofit.create(OfficeService::class.java)
    
    fun getTypes(): Call<OfficeTypeList> {
        return officeService.getTypes()
    }

    fun getBranches(): Call<BranchList> {
        return officeService.getBranches()
    }

    fun getLevels(): Call<OfficeLevelList> {
        return officeService.getLevels()
    }
}