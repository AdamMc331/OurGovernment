package com.adammcneilly.ourgovernment.models

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
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
                "{" +
                    "\"candidateList\":{" +
                        "\"generalInfo\":{" +
                            "\"title\":\"Project Vote Smart - Search Candidates\"," +
                            "\"linkBack\":\"http:\\/\\/votesmart.org\\/\"" +
                        "}," +
                        "\"candidate\":[" +
                            "{" +
                                "\"candidateId\":\"76596\"," +
                                "\"firstName\":\"James\"," +
                                "\"nickName\":\"\"," +
                                "\"middleName\":\"R.\"," +
                                "\"preferredName\":\"James\"," +
                                "\"lastName\":\"Fouts\"," +
                                "\"suffix\":\"\"," +
                                "\"title\":\"Mayor\"," +
                                "\"ballotName\":\"\"," +
                                "\"electionParties\":\"\"," +
                                "\"electionStatus\":\"\"," +
                                "\"electionStage\":\"\"," +
                                "\"electionDistrictId\":\"\"," +
                                "\"electionDistrictName\":\"\"," +
                                "\"electionOffice\":\"\"," +
                                "\"electionOfficeId\":\"\"," +
                                "\"electionStateId\":\"\"," +
                                "\"electionOfficeTypeId\":\"\"," +
                                "\"electionYear\":\"\"," +
                                "\"electionSpecial\":\"\"," +
                                "\"electionDate\":\"\"," +
                                "\"officeParties\":\"\"," +
                                "\"officeStatus\":\"\"," +
                                "\"officeDistrictId\":\"31279\"," +
                                "\"officeDistrictName\":\"At-Large\"," +
                                "\"officeStateId\":\"MI\"," +
                                "\"officeId\":\"73\"," +
                                "\"officeName\":\"Mayor\"," +
                                "\"officeTypeId\":\"M\"," +
                                "\"runningMateId\":\"\"," +
                                "\"runningMateName\":\"\"" +
                            "}" +
                        "]" +
                    "}" +
                "}")
    }

    override fun equals(other: Any?): Boolean {
        return (other is CandidateList) && list == other.list
    }

    override fun hashCode(): Int {
        return list.hashCode()
    }

    open class CandidateListDeserializer : JsonDeserializer<CandidateList> {
        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): CandidateList {
            val result = CandidateList()

            if (json != null && json.isJsonObject) {
                val root = json.asJsonObject

                if (root.has(CANDIDATE_LIST) && root.get(CANDIDATE_LIST).isJsonObject) {
                    val candidateList = root.get(CANDIDATE_LIST).asJsonObject

                    if (candidateList.has(CANDIDATE) && root.get(CANDIDATE).isJsonArray) {
                        val candidateArray = root.get(CANDIDATE).asJsonArray
                        candidateArray.mapTo(result.list) { Gson().fromJson(it, Candidate::class.java) }
                    }
                }
            }

            return result
        }

        companion object {
            val CANDIDATE_LIST = "candidateList"
            val CANDIDATE = "candidate"
        }
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

        override fun equals(other: Any?): Boolean {
            return (other is Candidate)
                    && candidateId == other.candidateId
                    && firstName == other.firstName
                    && lastName == other.lastName
                    && middleName == other.middleName
                    && nickName == other.nickName
                    && preferredName == other.preferredName
                    && suffix == other.suffix
                    && title == other.title
                    && ballotName == other.ballotName
                    && electionParties == other.electionParties
                    && electionStage == other.electionStage
                    && electionStatus == other.electionStatus
                    && electionStateId == other.electionStateId
                    && electionDate == other.electionDate
                    && electionDistrictId == other.electionDistrictId
                    && electionDistrictName == other.electionDistrictName
                    && electionOffice == other.electionOffice
                    && electionOfficeId == other.electionOfficeId
                    && electionOfficeTypeId == other.electionOfficeTypeId
                    && electionYear == other.electionYear
                    && electionSpecial == other.electionSpecial
                    && officeParties == other.officeParties
                    && officeStatus == other.officeStatus
                    && officeStateId == other.officeStateId
                    && officeDistrictId == other.officeDistrictId
                    && officeDistrictName == other.officeDistrictName
                    && officeId == other.officeId
                    && officeName == other.officeName
                    && officeTypeId == other.officeTypeId
                    && runningMateId == other.runningMateId
                    && runningMateName == other.runningMateName
        }

        override fun hashCode(): Int {
            return super.hashCode()
        }
    }
}