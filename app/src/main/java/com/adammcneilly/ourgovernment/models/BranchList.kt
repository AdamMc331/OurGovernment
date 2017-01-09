package com.adammcneilly.ourgovernment.models

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.util.*

/**
 * Represents a list of branches in our government.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
class BranchList : BaseModel() {

    var list: ArrayList<Branch> = ArrayList()

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

    override fun equals(other: Any?): Boolean {
        return (other is BranchList) && list == other.list
    }

    override fun hashCode(): Int {
        return list.hashCode()
    }

    open class BranchListDeserializer : JsonDeserializer<BranchList> {

        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): BranchList {
            val result = BranchList()

            if (json != null && json.isJsonObject) {
                val root = json.asJsonObject

                if (root.has(BRANCHES) && root.get(BRANCHES).isJsonObject) {
                    val branches = root.get(BRANCHES).asJsonObject

                    if (branches.has(BRANCH) && branches.get(BRANCH).isJsonArray) {
                        val branchArray = branches.get(BRANCH).asJsonArray
                        branchArray.mapTo(result.list) { Gson().fromJson(it, Branch::class.java) }
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

    open class Branch : BaseModel() {
        var officeBranchId = ""
        var name = ""

        override fun equals(other: Any?): Boolean {
            return (other is Branch) && name == other.name && officeBranchId == other.officeBranchId
        }

        override fun hashCode(): Int {
            return officeBranchId.hashCode() * name.hashCode()
        }
    }
}