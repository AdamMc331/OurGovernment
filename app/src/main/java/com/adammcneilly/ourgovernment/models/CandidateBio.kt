package com.adammcneilly.ourgovernment.models

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.util.*

/**
 * Detailed bio for a candidate
 *
 * Created by adam.mcneilly on 1/1/17.
 */
class CandidateBio : BaseModel() {
    // First three elements are used in getBio. Others are for getDetailedBio.
    var candidate = CandidateBio.Candidate()
    var office = CandidateBio.Office()
    var election = CandidateBio.Election()
    var education = CandidateBio.Education()
    var profession = CandidateBio.Profession()
    var political = CandidateBio.Profession()
    var congMembership = CandidateBio.Profession()
    var orgMembership = CandidateBio.Profession()

    override fun getSuccessJson(): List<String> {
        return listOf(
                "{" +
                    "\"bio\":{" +
                        "\"generalInfo\":{" +
                            "\"title\":\"Project Vote Smart - Bio -  James Fouts\"," +
                            "\"linkBack\":\"http:\\/\\/votesmart.org\\/bio.php?can_id=76596\"" +
                        "}," +
                        "\"candidate\":{" +
                            "\"candidateId\":\"76596\"," +
                            "\"crpId\":\"\"," +
                            "\"photo\":\"http:\\/\\/static.votesmart.org\\/canphoto\\/76596.jpg\"," +
                            "\"firstName\":\"James\"," +
                            "\"nickName\":\"\"," +
                            "\"middleName\":\"R.\"," +
                            "\"preferredName\":\"James\"," +
                            "\"lastName\":\"Fouts\"," +
                            "\"suffix\":\"\"," +
                            "\"birthDate\":\"\"," +
                            "\"birthPlace\":\"\"," +
                            "\"pronunciation\":\"\"," +
                            "\"gender\":\"\"," +
                            "\"family\":\"\"," +
                            "\"homeCity\":\"\"," +
                            "\"homeState\":\"MI\"," +
                            "\"education\":\"\"," +
                            "\"profession\":\"\"," +
                            "\"political\":\"\"," +
                            "\"religion\":\"\"," +
                            "\"congMembership\":\"\"," +
                            "\"orgMembership\":\"\"," +
                            "\"specialMsg\":\"\"" +
                        "}," +
                        "\"office\":{" +
                            "\"name\":[\"Mayor\",\"Mayor\"]," +
                            "\"parties\":\"\"," +
                            "\"title\":\"\"," +
                            "\"shortTitle\":\"\"," +
                            "\"type\":\"Local Executive\"," +
                            "\"status\":\"active\"," +
                            "\"firstElect\":\"\"," +
                            "\"lastElect\":\"\"," +
                            "\"nextElect\":\"\"," +
                            "\"termStart\":\"\"," +
                            "\"termEnd\":\"\"," +
                            "\"district\":\"At-Large\"," +
                            "\"districtId\":\"31279\"," +
                            "\"stateId\":\"MI\"" +
                        "}" +
                    "}" +
                "}")
    }

    override fun equals(other: Any?): Boolean {
        return (other is CandidateBio)
                && candidate == other.candidate
                && office == other.office
                && election == other.election
                && education == other.education
                && profession == other.profession
                && political == other.political
                && congMembership == other.congMembership
                && orgMembership == other.orgMembership
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    open class CandidateBioDeserializer : JsonDeserializer<CandidateBio> {
        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): CandidateBio {
            var result = CandidateBio()

            if (json != null && json.isJsonObject) {
                val root = json.asJsonObject

                if (root.has(BIO) && root.get(BIO).isJsonObject) {
                    val bio = root.get(BIO).asJsonObject

                    result = Gson().fromJson(bio, CandidateBio::class.java)
                }
            }

            return result
        }

        companion object {
            val BIO = "bio"
        }
    }

    open class Candidate {
        var candidateId = 0
        var firstName = ""
        var lastName = ""
        var crpID = ""
        var nickName = ""
        var middleName = ""
        var suffix = ""
        var birthDate = ""
        var birthPlace = ""
        var pronunciation = ""
        var gender = ""
        var family = ""
        var photo = ""
        var homeCity = ""
        var homeState = ""
        var religion = ""
        var specialMsg = ""

        override fun equals(other: Any?): Boolean {
            return (other is Candidate)
                    && candidateId == other.candidateId
                    && firstName == other.firstName
                    && crpID == other.crpID
                    && nickName == other.nickName
                    && middleName == other.middleName
                    && suffix == other.suffix
                    && birthDate == other.birthDate
                    && birthPlace == other.birthPlace
                    && pronunciation == other.pronunciation
                    && family == other.family
                    && gender == other.gender
                    && photo == other.photo
                    && homeCity == other.homeCity
                    && homeState == other.homeState
                    && religion == other.religion
                    && specialMsg == other.specialMsg
        }

        override fun hashCode(): Int {
            return super.hashCode()
        }
    }

    open class Office {
        var committee = CandidateBio.Office.Committee()
        var parties = ""
        var title = ""
        var shortTitle = ""
        var name: ArrayList<String> = ArrayList()
        var type = ""
        var status = ""
        var firstElect = ""
        var lastElect = ""
        var nextElect = ""
        var termStart = ""
        var termEnd = ""
        var district = ""
        var districtId = ""
        var stateId = ""

        override fun equals(other: Any?): Boolean {
            return (other is Office)
                    && committee == other.committee
                    && parties == other.parties
                    && title == other.title
                    && shortTitle == other.shortTitle
                    && name == other.name
                    && type == other.type
                    && status == other.status
                    && stateId == other.stateId
                    && firstElect == other.firstElect
                    && lastElect == other.lastElect
                    && nextElect == other.nextElect
                    && termStart == other.termStart
                    && termEnd == other.termEnd
                    && district == other.district
                    && districtId == other.districtId
        }

        override fun hashCode(): Int {
            return super.hashCode()
        }

        open class Committee {
            var committeeId = ""
            var committeeName = ""

            override fun equals(other: Any?): Boolean {
                return (other is Committee)
                        && committeeId == other.committeeId
                        && committeeName == other.committeeName
            }

            override fun hashCode(): Int {
                return committeeId.hashCode() * committeeName.hashCode()
            }
        }
    }

    open class Election {
        var office = ""
        var officeId = 0
        var officeType = ""
        var parties = ""
        var district = ""
        var districtId = 0
        var status = ""
        var ballotName = ""

        override fun equals(other: Any?): Boolean {
            return (other is Election)
                    && office == other.office
                    && officeId == other.officeId
                    && officeType == other.officeType
                    && parties == other.parties
                    && district == other.district
                    && districtId == other.districtId
                    && status == other.status
                    && ballotName == other.ballotName
        }

        override fun hashCode(): Int {
            return super.hashCode()
        }
    }

    open class Education {
        var degree = ""
        var field = ""
        var school = ""
        var span = ""
        var gpa = ""
        var fullText = ""

        override fun equals(other: Any?): Boolean {
            return (other is Education)
                    && degree == other.degree
                    && field == other.field
                    && school == other.school
                    && span == other.span
                    && gpa == other.gpa
                    && fullText == other.fullText
        }

        override fun hashCode(): Int {
            return super.hashCode()
        }
    }

    open class Profession {
        var title = ""
        var organization = ""
        var span = ""
        var special = ""
        var district = ""
        var fullText = ""

        override fun equals(other: Any?): Boolean {
            return (other is Profession)
                    && title == other.title
                    && organization == other.organization
                    && span == other.special
                    && special == other.special
                    && district == other.district
                    && fullText == other.fullText
        }

        override fun hashCode(): Int {
            return super.hashCode()
        }
    }
}