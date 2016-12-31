package com.adammcneilly.ourgovernment.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Represents a city/county.
 *
 * Created by adam.mcneilly on 12/28/16.
 */
@Root(strict=false)
open class County() : BaseModel() {
    @field:Element var localId = 0
    @field:Element var name = ""
    @field:Element var url = ""

    override fun toString(): String {
        return name
    }
}