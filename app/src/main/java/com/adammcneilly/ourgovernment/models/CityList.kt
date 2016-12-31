package com.adammcneilly.ourgovernment.models

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.util.*

/**
 * Represents a list of cities returned from the API
 *
 * Created by adam.mcneilly on 12/31/16.
 */
@Root(strict=false)
open class CityList() : BaseModel() {
    @field:ElementList(entry="city", inline=true) var list: ArrayList<County> = ArrayList()

    override fun getSuccessXml(): List<String> {
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
}