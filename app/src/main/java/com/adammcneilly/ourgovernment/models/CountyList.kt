package com.adammcneilly.ourgovernment.models

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.util.*

/**
 * Represents a list of counties returned by the API.
 *
 * Created by adam.mcneilly on 12/28/16.
 */
@Root(strict=false)
open class CountyList() : BaseModel() {
    @field:ElementList(entry="county", inline=true) var list: ArrayList<County> = ArrayList()
}