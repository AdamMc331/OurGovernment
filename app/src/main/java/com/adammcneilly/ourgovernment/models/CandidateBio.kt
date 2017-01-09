package com.adammcneilly.ourgovernment.models

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
                "<bio>" +
                        "<generalInfo>" +
                        "<title>Project Vote Smart - Bio - James Fouts</title>" +
                        "<linkBack>http://votesmart.org/bio.php?can_id=76596</linkBack>" +
                        "</generalInfo>" +
                        "<candidate>" +
                        "<candidateId>76596</candidateId>" +
                        "<crpId/>" +
                        "<photo>http://static.votesmart.org/canphoto/76596.jpg</photo>" +
                        "<firstName>James</firstName>" +
                        "<nickName/>" +
                        "<middleName>R.</middleName>" +
                        "<preferredName>James</preferredName>" +
                        "<lastName>Fouts</lastName>" +
                        "<suffix/>" +
                        "<birthDate/>" +
                        "<birthPlace/>" +
                        "<pronunciation/>" +
                        "<gender/>" +
                        "<family><![CDATA[ ]]></family>" +
                        "<homeCity/>" +
                        "<homeState>MI</homeState>" +
                        "<religion/>" +
                        "<specialMsg><![CDATA[ ]]></specialMsg>" +
                        "</candidate>" +
                        "<office>" +
                        "<name>Mayor</name>" +
                        "<parties/>" +
                        "<title/>" +
                        "<shortTitle/>" +
                        "<name>Mayor</name>" +
                        "<type>Local Executive</type>" +
                        "<status>active</status>" +
                        "<firstElect/>" +
                        "<lastElect/>" +
                        "<nextElect/>" +
                        "<termStart/>" +
                        "<termEnd/>" +
                        "<district>At-Large</district>" +
                        "<districtId>31279</districtId>" +
                        "<stateId>MI</stateId>" +
                        "</office>" +
                        "</bio>")
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
    }

    open class Office {
        var committee = CandidateBio.Office.Committee()
        var parties = ""
        var title = ""
        var shortTitle = ""
        var name = ""
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

        open class Committee {
            var committeeId = ""
            var committeeName = ""
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
    }

    open class Education {
        var degree = ""
        var field = ""
        var school = ""
        var span = ""
        var gpa = ""
        var fullText = ""
    }

    open class Profession {
        var title = ""
        var organization = ""
        var span = ""
        var special = ""
        var district = ""
        var fullText = ""
    }
}