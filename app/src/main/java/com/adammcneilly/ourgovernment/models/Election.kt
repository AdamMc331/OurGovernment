package com.adammcneilly.ourgovernment.models

/**
 * Represents an Election returned by the API.
 *
 * Created by adam.mcneilly on 1/7/17.
 */
open class Election : BaseModel() {
    var electionId = ""
    var name = ""
    var stateId = ""
    var officetypeId = ""
    var special = ""
    var electionYear = ""
    var stage = Stage()

    open class Stage : BaseModel() {
        var stageId = ""
        var name = ""
        var stateId = ""
        var electionDate = ""
        var filingDeadline = ""
        var npatMailed = ""
    }
}