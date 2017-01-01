package com.adammcneilly.ourgovernment.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.util.*

/**
 * Represents a list of counties returned by the API.
 *
 * Created by adam.mcneilly on 12/28/16.
 */
@Root(strict=false)
open class CountyList : BaseModel() {
    @field:ElementList(entry="county", inline=true) var list: ArrayList<CountyList.County> = ArrayList()

    override fun getSuccessXml(): List<String> {
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

    override fun getFailureXml(): List<String> {
        return listOf(
                "<error>" +
                    "<errorMessage>This probably shouldn't have happened.</errorMessage>" +
                "</error>")
    }

    @Root(strict=false)
    open class County : BaseModel() {
        @field:Element var localId = 0
        @field:Element var name = ""
        @field:Element var url = ""

        override fun toString(): String {
            return name
        }
    }
}