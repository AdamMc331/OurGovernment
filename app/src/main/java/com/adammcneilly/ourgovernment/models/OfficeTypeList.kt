package com.adammcneilly.ourgovernment.models

/**
 * Represents a list of Office Types
 *
 * Created by adam.mcneilly on 1/8/17.
 */
open class OfficeTypeList {

    open class OfficeType : BaseModel() {
        var officeTypeId = ""
        var officeLevelId = ""
        var officeBranchId = ""
        var name = ""
    }
}