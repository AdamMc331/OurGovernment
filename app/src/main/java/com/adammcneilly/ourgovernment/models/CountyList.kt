package com.adammcneilly.ourgovernment.models

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

/**
 * Represents a list of counties returned by the API.
 *
 * Created by adam.mcneilly on 12/28/16.
 */
open class CountyList : ProxyList<CountyList.County>() {

    override fun getSuccessJson(): List<String> {
        return listOf(
                "{" +
                    "\"counties\":{" +
                        "\"generalInfo\":{" +
                            "\"title\":\"Project Vote Smart - Counties -\"," +
                            "\"linkBack\":\"http:\\/\\/votesmart.org\\/official_local.php?state_id=\"" +
                        "}," +
                        "\"county\":[" +
                            "{" +
                                "\"localId\":\"2452\"," +
                                "\"name\":\"Alcona County\"," +
                                "\"url\":\"http:\\/\\/public.alconacountymi.com\\/\"" +
                            "}," +
                            "{" +
                                "\"localId\":\"2453\"," +
                                "\"name\":\"Alger County\"," +
                                "\"url\":\"http:\\/\\/algercourthouse.com\\/\"" +
                            "}," +
                            "{" +
                                "\"localId\":\"2454\"," +
                                "\"name\":\"Allegan County\"," +
                                "\"url\":\"http:\\/\\/www.allegancounty.org\\/Index.asp\"" +
                            "}" +
                        "]" +
                    "}" +
                "}")
    }

    open class CountyListDeserializer : JsonDeserializer<CountyList> {
        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): CountyList {
            val result = CountyList()

            if (json != null && json.isJsonObject) {
                val root = json.asJsonObject

                if (root.has(COUNTIES) && root.get(COUNTIES).isJsonObject) {
                    val counties = root.get(COUNTIES).asJsonObject

                    if (counties.has(COUNTY) && counties.get(COUNTY).isJsonArray) {
                        val countyArray = counties.get(COUNTY).asJsonArray
                        countyArray.mapTo(result) { Gson().fromJson(it, County::class.java) }
                    }
                }
            }

            return result
        }

        companion object {
            val COUNTIES = "counties"
            val COUNTY = "county"
        }
    }

    open class County : BaseModel() {
        var localId = 0
        var name = ""
        var url = ""

        override fun toString(): String {
            return name
        }

        override fun equals(other: Any?): Boolean {
            return (other is County)
                    && localId == other.localId
                    && name == other.name
                    && url == other.url
        }

        override fun hashCode(): Int {
            return localId.hashCode() * name.hashCode() * url.hashCode()
        }
    }
}