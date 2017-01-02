package com.adammcneilly.ourgovernment.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import org.simpleframework.xml.convert.Convert
import org.simpleframework.xml.convert.Converter
import org.simpleframework.xml.stream.InputNode
import org.simpleframework.xml.stream.OutputNode

/**
 * Detailed bio for a candidate
 *
 * Created by adam.mcneilly on 1/1/17.
 */
@Root(strict=false)
class CandidateBio : BaseModel() {
    @field:Element var candidate = CandidateBio.Candidate()
    @field:Element var office = CandidateBio.Office()
    @field:Element(required=false) var election = CandidateBio.Election()

    override fun getSuccessXml(): List<String> {
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

    @Root(strict=false)
    open class Candidate {
        @field:Element(required=false) var candidateId = 0
        @field:Element(required=false) var firstName = ""
        @field:Element(required=false) var lastName = ""
        @field:Element(required=false) var crpID = ""
        @field:Element(required=false) var nickName = ""
        @field:Element(required=false) var middleName = ""
        @field:Element(required=false) var suffix = ""
        @field:Element(required=false) var birthDate = ""
        @field:Element(required=false) var birthPlace = ""
        @field:Element(required=false) var pronunciation = ""
        @field:Element(required=false) var gender = ""
        @field:Element(required=false) var family = ""
        @field:Element(required=false) var photo = ""
        @field:Element(required=false) var homeCity = ""
        @field:Element(required=false) var homeState = ""
        @field:Element(required=false) var religion = ""
        @field:Element(required=false) var specialMsg = ""
    }

    @Root(strict=false)
    @Convert(Office.OfficeConverter::class)
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

        open class OfficeConverter : Converter<Office> {
            override fun read(node: InputNode?): Office {
                val office = Office()

                var nextNode = node?.next
                while (nextNode != null) {
                    when (nextNode.name) {
                        COMMITTEE -> office.committee = Committee.CommitteeConverter().read(nextNode)
                        PARTIES -> office.parties = nextNode.value.orEmpty()
                        TITLE -> office.title = nextNode.value.orEmpty()
                        SHORT_TITLE -> office.shortTitle = nextNode.value.orEmpty()
                        NAME -> office.name = nextNode.value.orEmpty()
                        TYPE -> office.type = nextNode.value.orEmpty()
                        STATUS -> office.status = nextNode.value.orEmpty()
                        FIRST_ELECT -> office.firstElect = nextNode.value.orEmpty()
                        LAST_ELECT -> office.lastElect = nextNode.value.orEmpty()
                        NEXT_ELECT -> office.nextElect = nextNode.value.orEmpty()
                        TERM_START -> office.termStart = nextNode.value.orEmpty()
                        TERM_END -> office.termEnd = nextNode.value.orEmpty()
                        DISTRICT -> office.district = nextNode.value.orEmpty()
                        DISTRICT_ID -> office.districtId = nextNode.value.orEmpty()
                        STATE_ID -> office.stateId = nextNode.value.orEmpty()
                    }

                    nextNode = node?.next
                }

                return office
            }

            override fun write(node: OutputNode?, value: Office?) {
                throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            companion object {
                val COMMITTEE = "committee"
                val PARTIES = "parties"
                val TITLE = "title"
                val SHORT_TITLE = "shortTitle"
                val NAME = "name"
                val TYPE = "type"
                val STATUS = "status"
                val FIRST_ELECT = "firstElect"
                val LAST_ELECT = "lastElect"
                val NEXT_ELECT = "nextElect"
                val TERM_START = "termStart"
                val TERM_END = "termEnd"
                val DISTRICT = "district"
                val DISTRICT_ID = "districtId"
                val STATE_ID = "stateId"
            }
        }

        @Root(strict=false)
        @Convert(Committee.CommitteeConverter::class)
        open class Committee {
            var committeeId = ""
            var committeeName = ""

            open class CommitteeConverter : Converter<Committee> {
                override fun write(node: OutputNode?, value: Committee?) {
                    throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun read(node: InputNode?): Committee {
                    val committee = Committee()

                    var nextNode = node?.next
                    while (nextNode != null) {
                        when (nextNode.name) {
                            COMMITTEE_ID -> committee.committeeId = nextNode.value.orEmpty()
                            COMMITTEE_NAME -> committee.committeeName = nextNode.value.orEmpty()
                        }

                        nextNode = node?.next
                    }

                    return committee
                }

                companion object {
                    val COMMITTEE_ID = "committeeId"
                    val COMMITTEE_NAME = "committeeName"
                }
            }
        }
    }

    @Root(strict=false)
    open class Election {
        @field:Element(required=false) var office = ""
        @field:Element(required=false) var officeId = 0
        @field:Element(required=false) var officeType = ""
        @field:Element(required=false) var parties = ""
        @field:Element(required=false) var district = ""
        @field:Element(required=false) var districtId = 0
        @field:Element(required=false) var status = ""
        @field:Element(required=false) var ballotName = ""
    }
}