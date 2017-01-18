package com.adammcneilly.ourgovernment.models

import android.os.Parcel
import com.adammcneilly.ourgovernment.utils.creator
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

/**
 * Represents a list of cities returned from the API
 *
 * Created by adam.mcneilly on 12/31/16.
 */
open class CityList : ProxyList<CityList.City> {

    constructor(): super()

    constructor(source: Parcel): super(source)

    override fun getSuccessJson(): List<String> {
        return listOf(
                "{" +
                    "\"cities\":{" +
                        "\"generalInfo\":{" +
                            "\"title\":\"Project Vote Smart - Cities -\"," +
                            "\"linkBack\":\"http:\\/\\/votesmart.org\\/official_local.php?state_id=\"" +
                        "}," +
                        "\"city\":[" +
                            "{" +
                                "\"localId\":\"620\"," +
                                "\"name\":\"Allen Park\"," +
                                "\"url\":\"http:\\/\\/www.cityofallenpark.org\\/\"" +
                            "}," +
                            "{" +
                                "\"localId\":\"621\"," +
                                "\"name\":\"Ann Arbor\"," +
                                "\"url\":\"http:\\/\\/www.a2gov.org\\/pages\\/default.aspx\"" +
                            "}," +
                            "{" +
                                "\"localId\":\"622\"," +
                                "\"name\":\"Battle Creek\"," +
                                "\"url\":\"http:\\/\\/www.battlecreekmi.gov\\/\"" +
                            "}" +
                        "]" +
                    "}" +
                "}")
    }

    companion object {
        @JvmField val CREATOR = creator(::CityList)
    }

    open class CityListDeserializer : JsonDeserializer<CityList> {
        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): CityList {
            val result = CityList()

            if (json != null && json.isJsonObject) {
                val root = json.asJsonObject

                if (root.has(CITIES) && root.get(CITIES).isJsonObject) {
                    val cities = root.get(CITIES).asJsonObject

                    if (cities.has(CITY) && cities.get(CITY).isJsonArray) {
                        val cityArray = cities.get(CITY).asJsonArray
                        cityArray.mapTo(result) { Gson().fromJson(it, City::class.java) }
                    }
                }
            }

            return result
        }

        companion object {
            val CITIES = "cities"
            val CITY = "city"
        }
    }

    open class City : BaseModel {
        var localId = ""
        var name = ""
        var url = ""

        constructor(): super()

        constructor(source: Parcel): super(source) {
            //TODO:
        }

        override fun toString(): String {
            return name
        }

        override fun equals(other: Any?): Boolean {
            return (other is City)
                    && localId == other.localId
                    && name == other.name
                    && url == other.url
        }

        override fun hashCode(): Int {
            return localId.hashCode() * name.hashCode() * url.hashCode()
        }

        companion object {
            @JvmField val CREATOR = creator(::City)
        }
    }
}