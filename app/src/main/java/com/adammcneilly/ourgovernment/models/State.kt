package com.adammcneilly.ourgovernment.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Represents all the information contained for a State.
 *
 * Created by adam.mcneilly on 12/29/16.
 */
@Root(name="details", strict=false)
open class State() : BaseModel() {
    @field:Element var details: Details = Details()

    override fun getSuccessXml(): List<String> {
        return listOf(
                "<state>" +
                    "<generalInfo>" +
                        "<title>Project Vote Smart - States</title>" +
                        "<linkBack>http://votesmart.org/mystate_statefacts.php?state_id=MI</linkBack>" +
                    "</generalInfo>" +
                    "<details>" +
                        "<stateId>MI</stateId>" +
                        "<stateType>State</stateType>" +
                        "<name>Michigan</name>" +
                        "<nickName>The Great Lake State</nickName>" +
                        "<capital>Lansing</capital>" +
                        "<area>58,216 sq mi</area>" +
                        "<population>9,883,360 (2012 est.)</population>" +
                        "<statehood>Jan. 26, 1837 (26th state)</statehood>" +
                        "<motto>Si Quaeris Peninsulam Amoenam Circumspice [If You Seek a Pleasant Peninsula, Look about You]</motto>" +
                        "<flower>Apple Blossom</flower>" +
                        "<tree/>" +
                        "<bird>Robin</bird>" +
                        "<highPoint>Mt. Arvon, 1,979 ft</highPoint>" +
                        "<lowPoint>Lake Erie, 572 ft</lowPoint>" +
                        "<bicameral>t</bicameral>" +
                        "<upperLegis>Legislature</upperLegis>" +
                        "<lowerLegis>House of Representatives</lowerLegis>" +
                        "<ltGov>t</ltGov>" +
                        "<senators>0</senators>" +
                        "<reps>0</reps>" +
                        "<termLimit>0</termLimit>" +
                        "<termLength>0</termLength>" +
                        "<billUrl/>" +
                        "<voteUrl/>" +
                        "<primaryDate/>" +
                        "<generalDate/>" +
                        "<largestCity/>" +
                        "<rollUpper>Roll no.</rollUpper>" +
                        "<rollLower>Roll no.</rollLower>" +
                        "<usCircuit>Sixth</usCircuit>" +
                    "</details>" +
                "</state>")
    }

    open class Details() {
        @field:Element var stateId = ""
        @field:Element var name = ""
        @field:Element(required=false) var stateType = ""
        @field:Element(required=false) var nickName = ""
        @field:Element(required=false) var capital = ""
        @field:Element(required=false) var area = ""
        @field:Element(required=false) var population = ""
        @field:Element(required=false) var statehood = ""
        @field:Element(required=false) var motto = ""
        @field:Element(required=false) var flower = ""
        @field:Element(required=false) var tree = ""
        @field:Element(required=false) var bird = ""
        @field:Element(required=false) var highPoint = ""
        @field:Element(required=false) var lowPoint = ""
        @field:Element(required=false) var bicameral = ""
        @field:Element(required=false) var upperLegis = ""
        @field:Element(required=false) var lowerLegis = ""
        @field:Element(required=false) var ltGov = ""
        @field:Element(required=false) var senators = ""
        @field:Element(required=false) var reps = ""
        @field:Element(required=false) var termLimit = ""
        @field:Element(required=false) var termLength = ""
        @field:Element(required=false) var billUrl = ""
        @field:Element(required=false) var voteUrl = ""
        @field:Element(required=false) var voterReg = ""
        @field:Element(required=false) var primaryDate = ""
        @field:Element(required=false) var generalDate = ""
        @field:Element(required=false) var absenteeWho = ""
        @field:Element(required=false) var absenteeHow = ""
        @field:Element(required=false) var absenteeWhen = ""
        @field:Element(required=false) var largestCity = ""
        @field:Element(required=false) var rollUpper = ""
        @field:Element(required=false) var rollLower = ""
        @field:Element(required=false) var usCircuit = ""
    }
}