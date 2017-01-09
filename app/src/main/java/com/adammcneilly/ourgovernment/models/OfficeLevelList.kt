package com.adammcneilly.ourgovernment.models

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.util.*

/**
 * Represents a list of office levels.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
class OfficeLevelList : BaseModel() {

    var list: ArrayList<OfficeLevel> = ArrayList()

    override fun getSuccessJson(): List<String> {
        return listOf(
                "{" +
                    "\"levels\":{" +
                        "\"generalInfo\":{" +
                            "\"title\":\"Project Vote Smart - Offices\"," +
                            "\"linkBack\":\"http:\\/\\/votesmart.org\\/\"" +
                        "}," +
                        "\"level\":[" +
                            "{" +
                                "\"officeLevelId\":\"F\"," +
                                "\"name\":\"Federal\"" +
                            "}," +
                            "{" +
                                "\"officeLevelId\":\"S\"," +
                                "\"name\":\"State\"" +
                            "}," +
                            "{" +
                                "\"officeLevelId\":\"L\"," +
                                "\"name\":\"Local\"" +
                            "}" +
                        "]" +
                    "}" +
                "}")
    }

    override fun equals(other: Any?): Boolean {
        return (other is OfficeLevelList) && list == other.list
    }

    override fun hashCode(): Int {
        return list.hashCode()
    }

    open class OfficeLevelListDeserializer : JsonDeserializer<OfficeLevelList> {

        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OfficeLevelList {
            val result = OfficeLevelList()

            if (json != null && json.isJsonObject) {
                val root = json.asJsonObject

                if (root.has(LEVELS) && root.get(LEVELS).isJsonObject) {
                    val levels = root.get(LEVELS).asJsonObject

                    if (levels.has(LEVEL) && levels.get(LEVEL).isJsonArray) {
                        val levelArray = levels.get(LEVEL).asJsonArray
                        levelArray.mapTo(result.list) { Gson().fromJson(it, OfficeLevel::class.java) }
                    }
                }
            }

            return result
        }

        companion object {
            private val LEVELS = "levels"
            private val LEVEL = "level"
        }
    }

    open class OfficeLevel : BaseModel() {
        var officeLevelId = ""
        var name = ""

        override fun equals(other: Any?): Boolean {
            return (other is OfficeLevel) && officeLevelId == other.officeLevelId && name == other.name
        }

        override fun hashCode(): Int {
            return officeLevelId.hashCode() * name.hashCode()
        }
    }
}