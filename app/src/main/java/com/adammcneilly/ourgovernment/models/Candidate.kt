package com.adammcneilly.ourgovernment.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Represents a candidate returned from the api
 *
 * Created by adam.mcneilly on 12/28/16.
 */
@Root(strict=false)
open class Candidate() : BaseModel() {
    @field:Element var candidateId = 0
    @field:Element var firstName = ""
    @field:Element var nickName = ""
    @field:Element var middleName = ""
    @field:Element var preferredName = ""
    @field:Element var lastName = ""
    @field:Element var suffix = ""
    @field:Element var title = ""
    @field:Element var ballotName = ""
    @field:Element var electionParties = ""
    @field:Element var electionStatus = ""
    @field:Element var electionStage = ""
    @field:Element var electionDistrictId = ""
    @field:Element var electionDistrictName = ""
    @field:Element var electionOffice = ""
    @field:Element var electionOfficeId = ""
    @field:Element var electionStateId = ""
    @field:Element var electionOfficeTypeId = ""
    @field:Element var electionYear = ""
    @field:Element var electionSpecial = ""
    @field:Element var electionDate = ""
    @field:Element var officeParties = ""
    @field:Element var officeStatus = ""
    @field:Element var officeDistrictId = 0
    @field:Element var officeDistrictName = ""
    @field:Element var officeStateId = ""
    @field:Element var officeId = 0
    @field:Element var officeName = ""
    @field:Element var officeTypeId = ""
    @field:Element var runningMateId = 0
    @field:Element var runningMateName = ""
}