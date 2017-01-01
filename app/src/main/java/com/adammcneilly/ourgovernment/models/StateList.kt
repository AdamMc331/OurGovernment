package com.adammcneilly.ourgovernment.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.util.*

/**
 * Represents a group of states returned from the API.
 *
 * Created by adam.mcneilly on 12/26/16.
 */
@Root(strict=false)
open class StateList : BaseModel() {
    @field:ElementList var list: ArrayList<StateList.State> = ArrayList()

    override fun getSuccessXml(): List<String> {
        return listOf(
                "<stateList>" +
                    "<generalInfo>" +
                        "<title>Project Vote Smart - States</title>" +
                        "<linkBack>http://votesmart.org/mystate_statefacts.php</linkBack>" +
                    "</generalInfo>" +
                    "<list>" +
                        "<state>" +
                            "<stateId>MI</stateId>" +
                            "<name>Michigan</name>" +
                        "</state>" +
                        "<state>" +
                            "<stateId>OH</stateId>" +
                            "<name>Ohio Samoa</name>" +
                        "</state>" +
                        "<state>" +
                            "<stateId>FL</stateId>" +
                            "<name>Florida</name>" +
                        "</state>" +
                    "</list>" +
                "</stateList>")
    }

    @Root(strict=false)
    open class State : BaseModel() {
        @field:Element var stateId: String = ""
        @field:Element var name: String = ""

        override fun toString(): String {
            return name
        }
    }
}