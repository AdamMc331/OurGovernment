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
        @field:Element var stateType = ""
        @field:Element var nickName = ""
        @field:Element var capital = ""
        @field:Element var area = ""
        @field:Element var population = ""
        @field:Element var statehood = ""
        @field:Element var motto = ""
        @field:Element var flower = ""
        @field:Element var tree = ""
        @field:Element var bird = ""
        @field:Element var highPoint = ""
        @field:Element var lowPoint = ""
        @field:Element var bicameral = ""
        @field:Element var upperLegis = ""
        @field:Element var lowerLEgis = ""
        @field:Element var ltGov = ""
        @field:Element var senators = ""
        @field:Element var reps = ""
        @field:Element var termLimit = ""
        @field:Element var termLength = ""
        @field:Element var billUrl = ""
        @field:Element var voteUrl = ""
        @field:Element var voterReg = ""
        @field:Element var primaryDate = ""
        @field:Element var generalDate = ""
        @field:Element var absenteeWho = ""
        @field:Element var absenteeHow = ""
        @field:Element var absenteeWhen = ""
        @field:Element var largestCity = ""
        @field:Element var rollUpper = ""
        @field:Element var rollLower = ""
        @field:Element var usCircuit = ""
    }
}