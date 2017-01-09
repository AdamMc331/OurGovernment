package com.adammcneilly.ourgovernment.models

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

/**
 * Represents all the information contained for a State.
 *
 * Created by adam.mcneilly on 12/29/16.
 */
open class State : BaseModel() {
    var details: Details = Details()

    override fun getSuccessJson(): List<String> {
        return listOf(
                "{" +
                        "\"state\":{" +
                        "\"generalInfo\":{" +
                        "\"title\":\"Project Vote Smart - States\"," +
                        "\"linkBack\":\"http:\\/\\/votesmart.org\\/mystate_statefacts.php?state_id=MI\"" +
                        "}," +
                        "\"details\":{" +
                        "\"stateId\":\"MI\"," +
                        "\"stateType\":\"State\"," +
                        "\"name\":\"Michigan\"," +
                        "\"nickName\":\"The Great Lake State\"," +
                        "\"capital\":\"Lansing\"," +
                        "\"area\":\"58,216 sq mi\"," +
                        "\"population\":\"9,883,360 (2012 est.)\"," +
                        "\"statehood\":\"Jan. 26, 1837 (26th state)\"," +
                        "\"motto\":\"Si Quaeris Peninsulam Amoenam Circumspice [If You Seek a Pleasant Peninsula, Look about You]\"," +
                        "\"flower\":\"Apple Blossom\"," +
                        "\"tree\":\"\"," +
                        "\"bird\":\"Robin\"," +
                        "\"highPoint\":\"Mt. Arvon, 1,979 ft\"," +
                        "\"lowPoint\":\"Lake Erie, 572 ft\"," +
                        "\"bicameral\":\"t\"," +
                        "\"upperLegis\":\"Legislature\"," +
                        "\"lowerLegis\":\"House of Representatives\"," +
                        "\"ltGov\":\"t\"," +
                        "\"senators\":\"0\"," +
                        "\"reps\":\"0\"," +
                        "\"termLimit\":\"0\"," +
                        "\"termLength\":\"0\"," +
                        "\"billUrl\":\"\"," +
                        "\"voteUrl\":\"\"," +
                        "\"primaryDate\":\"\"," +
                        "\"generalDate\":\"\"," +
                        "\"largestCity\":\"\"," +
                        "\"rollUpper\":\"Roll no.\"," +
                        "\"rollLower\":\"Roll no.\"," +
                        "\"usCircuit\":\"Sixth\"" +
                        "}" +
                        "}" +
                        "}")
    }

    override fun equals(other: Any?): Boolean {
        return (other is State) && details == other.details
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    open class StateDeserializer : JsonDeserializer<State> {
        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): State {
            var response = State()

            if (json != null && json.isJsonObject) {
                val root = json.asJsonObject

                if (root.has(STATE) && root.get(STATE).isJsonObject) {
                    val stateObj = root.get(STATE).asJsonObject

                    response = Gson().fromJson(stateObj, State::class.java)
                }
            }

            return response
        }

        companion object {
            private val STATE = "state"
        }
    }

    open class Details {
        var stateId = ""
        var name = ""
        var stateType = ""
        var nickName = ""
        var capital = ""
        var area = ""
        var population = ""
        var statehood = ""
        var motto = ""
        var flower = ""
        var tree = ""
        var bird = ""
        var highPoint = ""
        var lowPoint = ""
        var bicameral = ""
        var upperLegis = ""
        var lowerLegis = ""
        var ltGov = ""
        var senators = ""
        var reps = ""
        var termLimit = ""
        var termLength = ""
        var billUrl = ""
        var voteUrl = ""
        var voterReg = ""
        var primaryDate = ""
        var generalDate = ""
        var absenteeWho = ""
        var absenteeHow = ""
        var absenteeWhen = ""
        var largestCity = ""
        var rollUpper = ""
        var rollLower = ""
        var usCircuit = ""

        override fun equals(other: Any?): Boolean {
            return (other is Details)
                    && stateId == other.stateId
                    && name == other.name
                    && stateType == other.stateType
                    && nickName == other.nickName
                    && capital == other.capital
                    && area == other.area
                    && population == other.population
                    && statehood == other.statehood
                    && motto == other.motto
                    && flower == other.flower
                    && tree == other.tree
                    && bird == other.bird
                    && highPoint == other.highPoint
                    && lowPoint == other.lowPoint
                    && bicameral == other.bicameral
                    && upperLegis == other.upperLegis
                    && lowerLegis == other.lowerLegis
                    && ltGov == other.ltGov
                    && senators == other.senators
                    && reps == other.reps
                    && termLimit == other.termLimit
                    && termLength == other.termLength
                    && billUrl == other.billUrl
                    && voteUrl == other.voteUrl
                    && voterReg == other.voterReg
                    && primaryDate == other.primaryDate
                    && generalDate == other.generalDate
                    && absenteeWho == other.absenteeWho
                    && absenteeWhen == other.absenteeWhen
                    && absenteeHow == other.absenteeHow
                    && largestCity == other.largestCity
                    && rollUpper == other.rollUpper
                    && rollLower == other.rollLower
                    && usCircuit == other.usCircuit
        }

        override fun hashCode(): Int {
            return super.hashCode()
        }
    }
}