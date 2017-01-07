package com.adammcneilly.ourgovernment.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Represents an Election returned by the API.
 *
 * Created by adam.mcneilly on 1/7/17.
 */
@Root(strict=false)
open class Election : BaseModel() {
    @field:Element var electionId = ""
    @field:Element var name = ""
    @field:Element var stateId = ""
    @field:Element var officetypeId = ""
    @field:Element var special = ""
    @field:Element var electionYear = ""
    @field:Element var stage = Stage()

    @Root(strict=false)
    open class Stage : BaseModel() {
        @field:Element var stageId = ""
        @field:Element var name = ""
        @field:Element var stateId = ""
        @field:Element var electionDate = ""
        @field:Element var filingDeadline = ""
        @field:Element var npatMailed = ""
    }
}