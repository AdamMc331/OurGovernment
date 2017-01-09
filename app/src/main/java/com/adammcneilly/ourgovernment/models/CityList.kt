package com.adammcneilly.ourgovernment.models

import java.util.*

/**
 * Represents a list of cities returned from the API
 *
 * Created by adam.mcneilly on 12/31/16.
 */
open class CityList : BaseModel() {
    var list: ArrayList<CityList.City> = ArrayList()

    override fun getSuccessJson(): List<String> {
        return listOf(
                "<cities>" +
                    "<generalInfo>" +
                        "<title>Project Vote Smart - Cities -</title>" +
                        "<linkBack>http://votesmart.org/official_local.php?state_id=</linkBack>" +
                    "</generalInfo>" +
                    "<city>" +
                        "<localId>620</localId>" +
                        "<name>Allen Park</name>" +
                        "<url>http://www.cityofallenpark.org/</url>" +
                    "</city>" +
                    "<city>" +
                        "<localId>621</localId>" +
                        "<name>Ann Arbor</name>" +
                        "<url>http://www.a2gov.org/pages/default.aspx</url>" +
                    "</city>" +
                    "<city>" +
                        "<localId>622</localId>" +
                        "<name>Battle Creek</name>" +
                        "<url>http://www.battlecreekmi.gov/</url>" +
                    "</city>" +
                "</cities>")
    }

    open class City : BaseModel() {
        var localId = 0
        var name = ""
        var url = ""

        override fun toString(): String {
            return name
        }
    }
}