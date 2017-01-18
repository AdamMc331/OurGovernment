package com.adammcneilly.ourgovernment.models

import android.os.Parcel
import com.adammcneilly.ourgovernment.utils.creator
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

/**
 * Represents a group of states returned from the API.
 *
 * Created by adam.mcneilly on 12/26/16.
 */
open class StateList : ProxyList<StateList.State> {

    constructor(): super()

    constructor(source: Parcel): super(source)

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

    companion object {
        val CREATOR = creator(::StateList)
    }

    open class State() : BaseModel() {
        var stateId: String = ""
        var name: String = ""

        constructor(source: Parcel): this() {
            stateId = source.readString()
            name = source.readString()
        }

        override fun toString(): String {
            return name
        }

        override fun equals(other: Any?): Boolean {
            return (other is State) && stateId == other.stateId && name == other.name
        }

        override fun hashCode(): Int {
            return stateId.hashCode() * name.hashCode()
        }

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            dest?.writeString(stateId)
            dest?.writeString(name)
        }

        companion object {
            @JvmField val CREATOR = creator(::State)
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

                            stateArray.mapTo(response) { Gson().fromJson(it, State::class.java) }
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