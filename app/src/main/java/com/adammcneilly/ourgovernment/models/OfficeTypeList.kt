package com.adammcneilly.ourgovernment.models

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

/**
 * Represents a list of Office Types
 *
 * Created by adam.mcneilly on 1/8/17.
 */
open class OfficeTypeList : ProxyList<OfficeTypeList.OfficeType>() {

    override fun getSuccessJson(): List<String> {
        return listOf(
                "{" +
                    "\"officeTypes\":{" +
                        "\"generalInfo\":{" +
                            "\"title\":\"Project Vote Smart - Offices\"," +
                            "\"linkBack\":\"http:\\/\\/votesmart.org\\/\"" +
                        "}," +
                        "\"type\":[" +
                            "{" +
                                "\"officeTypeId\":\"P\"," +
                                "\"officeLevelId\":\"F\"," +
                                "\"officeBranchId\":\"E\"," +
                                "\"name\":\"Presidential and Cabinet\"" +
                            "}," +
                            "{" +
                                "\"officeTypeId\":\"C\"," +
                                "\"officeLevelId\":\"F\"," +
                                "\"officeBranchId\":\"L\"," +
                                "\"name\":\"Congressional\"" +
                            "}," +
                            "{" +
                                "\"officeTypeId\":\"J\"," +
                                "\"officeLevelId\":\"F\"," +
                                "\"officeBranchId\":\"J\"," +
                                "\"name\":\"Supreme Court\"" +
                            "}" +
                        "]" +
                    "}" +
                "}")
    }

    open class OfficeType : BaseModel() {
        var officeTypeId = ""
        var officeLevelId = ""
        var officeBranchId = ""
        var name = ""

        override fun equals(other: Any?): Boolean {
            return (other is OfficeType)
                    && officeTypeId == other.officeTypeId
                    && officeLevelId == other.officeLevelId
                    && officeBranchId == other.officeBranchId
                    && name == other.name
        }

        override fun hashCode(): Int {
            return officeTypeId.hashCode() * officeLevelId.hashCode() * officeBranchId.hashCode() * name.hashCode()
        }
    }

    open class OfficeTypeListDeserializer : JsonDeserializer<OfficeTypeList> {
        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OfficeTypeList {
            val result = OfficeTypeList()

            if (json != null && json.isJsonObject) {
                val root = json.asJsonObject

                if (root.has(OFFICE_TYPES) && root.get(OFFICE_TYPES).isJsonObject) {
                    val officeTypes = root.get(OFFICE_TYPES).asJsonObject

                    if (officeTypes.has(TYPE) && officeTypes.get(TYPE).isJsonArray) {
                        val types = officeTypes.get(TYPE).asJsonArray
                        types.mapTo(result) { Gson().fromJson(it, OfficeType::class.java) }
                    }
                }
            }

            return result
        }

        companion object {
            private val OFFICE_TYPES = "officeTypes"
            private val TYPE = "type"
        }
    }
}