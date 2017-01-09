package com.adammcneilly.ourgovernment.models

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.util.*

/**
 * Represents a group of states returned from the API.
 *
 * Created by adam.mcneilly on 12/26/16.
 */
open class StateList : BaseModel() {
    var list: ArrayList<StateList.State> = ArrayList()

    override fun getSuccessJson(): List<String> {
        return listOf(
                "{" +
                    "\"stateList\":{" +
                        "\"generalInfo\": {" +
                            "\"title\":\"Project Vote Smart - States\"," +
                            "\"linkBack\":\"http:\\/\\/votesmart.org\\/mystate_statefacts.php\"" +
                        "}," +
                        "\"list\": {" +
                            "\"state\":[" +
                                "{\"stateId\":\"MI\",\"name\":\"Michigan\"}," +
                                "{\"stateId\":\"OH\",\"name\":\"Ohio\"}," +
                                "{\"stateId\":\"FL\",\"name\":\"Florida\"}" +
                            "]" +
                        "}" +
                    "}" +
                "}")
    }

    override fun equals(other: Any?): Boolean {
        return (other is StateList) && list == other.list
    }

    override fun hashCode(): Int {
        return list.hashCode()
    }

    open class State : BaseModel() {
        var stateId: String = ""
        var name: String = ""

        override fun toString(): String {
            return name
        }

        override fun equals(other: Any?): Boolean {
            return (other is State) && stateId == other.stateId && name == other.name
        }

        override fun hashCode(): Int {
            return stateId.hashCode() * name.hashCode()
        }
    }

    open class StateListDeserializer : JsonDeserializer<StateList> {
        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): StateList {
            val response = StateList()

            if (json != null && json.isJsonObject) {
                val root = json.asJsonObject

                if (root.has(STATE_LIST) && root.get(STATE_LIST).isJsonObject) {
                    val stateList = root.get(STATE_LIST).asJsonObject

                    if(stateList.has(LIST) && stateList.get(LIST).isJsonObject) {
                        val listObj = stateList.get(LIST).asJsonObject

                        if (listObj.has(STATE) && listObj.get(STATE).isJsonArray) {
                            val stateArray = listObj.get(STATE).asJsonArray

                            stateArray.mapTo(response.list) { Gson().fromJson(it, State::class.java) }
                        }
                    }
                }
            }

            return response
        }

        companion object {
            private val STATE_LIST = "stateList"
            private val LIST = "list"
            private val STATE = "state"
        }
    }
}