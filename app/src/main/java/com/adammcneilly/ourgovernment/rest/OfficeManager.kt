package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.OfficeBranchList
import com.adammcneilly.ourgovernment.models.OfficeLevelList
import com.adammcneilly.ourgovernment.models.OfficeTypeList
import rx.Observable

/**
 * Handles all of the Office calls.
 * 
 * Created by adam.mcneilly on 1/8/17.
 */
class OfficeManager : BaseManager() {
    private val officeService: OfficeService = retrofit.create(OfficeService::class.java)
    
    fun getTypes(): Observable<OfficeTypeList> {
        return officeService.getTypes()
    }

    fun getBranches(): Observable<OfficeBranchList> {
        return officeService.getBranches()
    }

    fun getLevels(): Observable<OfficeLevelList> {
        return officeService.getLevels()
    }
}