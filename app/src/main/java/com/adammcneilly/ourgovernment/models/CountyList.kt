package com.adammcneilly.ourgovernment.models

import java.util.*

/**
 * Represents a list of counties returned by the API.
 *
 * Created by adam.mcneilly on 12/28/16.
 */
open class CountyList : BaseModel() {
    var list: ArrayList<CountyList.County> = ArrayList()

    override fun getSuccessJson(): List<String> {
        return listOf(
                "<counties>" +
                    "<generalInfo>" +
                        "<title>Project Vote Smart - Counties -</title>" +
                        "<linkBack>http://votesmart.org/official_local.php?state_id=</linkBack>" +
                    "</generalInfo>" +
                    "<county>" +
                        "<localId>2452</localId>" +
                        "<name>Alcona County</name>" +
                        "<url>http://public.alconacountymi.com/</url>" +
                    "</county>" +
                    "<county>" +
                        "<localId>2453</localId>" +
                        "<name>Alger County</name>" +
                        "<url>http://algercourthouse.com/</url>" +
                    "</county>" +
                    "<county>" +
                        "<localId>2454</localId>" +
                        "<name>Allegan County</name>" +
                        "<url>http://www.allegancounty.org/Index.asp</url>" +
                    "</county>" +
                "</counties>")
    }

    override fun getFailureJson(): List<String> {
        return listOf(
                "<error>" +
                    "<errorMessage>This probably shouldn't have happened.</errorMessage>" +
                "</error>")
    }

    open class County : BaseModel() {
        var localId = 0
        var name = ""
        var url = ""

        override fun toString(): String {
            return name
        }
    }
}