package com.adammcneilly.ourgovernment.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.util.*

/**
 * Represents a list of candidates returned from the API
 *
 * Created by adam.mcneilly on 12/28/16.
 */
@Root(strict=false)
open class CandidateList : BaseModel() {
    @field:ElementList(entry="candidate", inline=true) var list: ArrayList<CandidateList.Candidate> = ArrayList()

    override fun getSuccessXml(): List<String> {
        return listOf(
                "<candidateList>" +
                    "<generalInfo>" +
                        "<title>Project Vote Smart - Search Candidates</title>" +
                        "<linkBack>http://votesmart.org/</linkBack>" +
                    "</generalInfo>" +
                    "<candidate>" +
                        "<candidateId>76596</candidateId>" +
                        "<firstName>James</firstName>" +
                        "<nickName/>" +
                        "<middleName>R.</middleName>" +
                        "<preferredName>James</preferredName>" +
                        "<lastName>Fouts</lastName>" +
                        "<suffix/>" +
                        "<title>Mayor</title>" +
                        "<ballotName/>" +
                        "<electionParties/>" +
                        "<electionStatus/>" +
                        "<electionStage/>" +
                        "<electionDistrictId/>" +
                        "<electionDistrictName/>" +
                        "<electionOffice/>" +
                        "<electionOfficeId/>" +
                        "<electionStateId/>" +
                        "<electionOfficeTypeId/>" +
                        "<electionYear/>" +
                        "<electionSpecial/>" +
                        "<electionDate/>" +
                        "<officeParties/>" +
                        "<officeStatus/>" +
                        "<officeDistrictId>31279</officeDistrictId>" +
                        "<officeDistrictName>At-Large</officeDistrictName>" +
                        "<officeStateId>MI</officeStateId>" +
                        "<officeId>73</officeId>" +
                        "<officeName>Mayor</officeName>" +
                        "<officeTypeId>M</officeTypeId>" +
                        "<runningMateId/>" +
                        "<runningMateName/>" +
                    "</candidate>" +
                "</candidateList>")
    }

    @Root(strict=false)
    open class Candidate : BaseModel() {
        @field:Element var candidateId = 0
        @field:Element var firstName = ""
        @field:Element var lastName = ""
        @field:Element(required=false) var nickName = ""
        @field:Element(required=false) var middleName = ""
        @field:Element(required=false) var preferredName = ""
        @field:Element(required=false) var suffix = ""
        @field:Element(required=false) var title = ""
        @field:Element(required=false) var ballotName = ""
        @field:Element(required=false) var electionParties = ""
        @field:Element(required=false) var electionStatus = ""
        @field:Element(required=false) var electionStage = ""
        @field:Element(required=false) var electionDistrictId = ""
        @field:Element(required=false) var electionDistrictName = ""
        @field:Element(required=false) var electionOffice = ""
        @field:Element(required=false) var electionOfficeId = ""
        @field:Element(required=false) var electionStateId = ""
        @field:Element(required=false) var electionOfficeTypeId = ""
        @field:Element(required=false) var electionYear = ""
        @field:Element(required=false) var electionSpecial = ""
        @field:Element(required=false) var electionDate = ""
        @field:Element(required=false) var officeParties = ""
        @field:Element(required=false) var officeStatus = ""
        @field:Element(required=false) var officeDistrictId = 0
        @field:Element(required=false) var officeDistrictName = ""
        @field:Element(required=false) var officeStateId = ""
        @field:Element(required=false) var officeId = 0
        @field:Element(required=false) var officeName = ""
        @field:Element(required=false) var officeTypeId = ""
        @field:Element(required=false) var runningMateId = 0
        @field:Element(required=false) var runningMateName = ""
    }
}