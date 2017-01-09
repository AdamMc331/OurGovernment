package com.adammcneilly.ourgovernment.models

import java.util.*

/**
 * Represents a list of candidates returned from the API
 *
 * Created by adam.mcneilly on 12/28/16.
 */
open class CandidateList : BaseModel() {
    var list: ArrayList<CandidateList.Candidate> = ArrayList()

    override fun getSuccessJson(): List<String> {
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

    open class Candidate : BaseModel() {
        var candidateId = 0
        var firstName = ""
        var lastName = ""
        var nickName = ""
        var middleName = ""
        var preferredName = ""
        var suffix = ""
        var title = ""
        var ballotName = ""
        var electionParties = ""
        var electionStatus = ""
        var electionStage = ""
        var electionDistrictId = ""
        var electionDistrictName = ""
        var electionOffice = ""
        var electionOfficeId = ""
        var electionStateId = ""
        var electionOfficeTypeId = ""
        var electionYear = ""
        var electionSpecial = ""
        var electionDate = ""
        var officeParties = ""
        var officeStatus = ""
        var officeDistrictId = 0
        var officeDistrictName = ""
        var officeStateId = ""
        var officeId = 0
        var officeName = ""
        var officeTypeId = ""
        var runningMateId = 0
        var runningMateName = ""
    }
}