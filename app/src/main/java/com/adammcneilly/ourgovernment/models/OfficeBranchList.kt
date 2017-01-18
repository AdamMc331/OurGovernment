package com.adammcneilly.ourgovernment.models

import android.os.Parcel
import com.adammcneilly.ourgovernment.utils.creator
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

/**
 * Represents a list of branches in our government.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
class OfficeBranchList : ProxyList<OfficeBranchList.OfficeBranch> {

    constructor(): super()

    constructor(source: Parcel): super(source)

    override fun getSuccessJson(): List<String> {
        return listOf(
                "{" +
                    "\"branches\":{" +
                        "\"generalInfo\":{" +
                            "\"title\":\"Project Vote Smart - Offices\"," +
                            "\"linkBack\":\"http:\\/\\/votesmart.org\\/\"" +
                        "}," +
                        "\"branch\":[" +
                            "{" +
                                "\"officeBranchId\":\"E\"," +
                                "\"name\":\"Executive\"" +
                            "}," +
                            "{" +
                                "\"officeBranchId\":\"L\"," +
                                "\"name\":\"Legislative\"" +
                            "}," +
                            "{" +
                                "\"officeBranchId\":\"J\"," +
                                "\"name\":\"Judicial\"" +
                            "}" +
                        "]" +
                    "}" +
                "}")
    }

    companion object {
        @JvmField val CREATOR = creator(::OfficeBranchList)
    }

    open class OfficeBranchListDeserializer : JsonDeserializer<OfficeBranchList> {

        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OfficeBranchList {
            val result = OfficeBranchList()

            if (json != null && json.isJsonObject) {
                val root = json.asJsonObject

                if (root.has(BRANCHES) && root.get(BRANCHES).isJsonObject) {
                    val branches = root.get(BRANCHES).asJsonObject

                    if (branches.has(BRANCH) && branches.get(BRANCH).isJsonArray) {
                        val branchArray = branches.get(BRANCH).asJsonArray
                        branchArray.mapTo(result) { Gson().fromJson(it, OfficeBranch::class.java) }
                    }
                }
            }

            return result
        }

        companion object {
            val BRANCHES = "branches"
            val BRANCH = "branch"
        }
    }

    open class OfficeBranch : BaseModel {
        var officeBranchId = ""
        var name = ""

        constructor(): super()

        constructor(source: Parcel): super(source) {
            //TODO:
        }

        override fun equals(other: Any?): Boolean {
            return (other is OfficeBranch) && name == other.name && officeBranchId == other.officeBranchId
        }

        override fun hashCode(): Int {
            return officeBranchId.hashCode() * name.hashCode()
        }

        companion object {
            @JvmField val CREATOR = creator(::OfficeBranch)
        }
    }
}