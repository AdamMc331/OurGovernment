package com.adammcneilly.ourgovernment.models

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.util.*

/**
 * Represents a list of candidates returned from the API
 *
 * Created by adam.mcneilly on 12/28/16.
 */
@Root(strict=false)
open class CandidateList() : BaseModel() {
    @field:ElementList(entry="candidate", inline=true) var list: ArrayList<Candidate> = ArrayList()

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
}