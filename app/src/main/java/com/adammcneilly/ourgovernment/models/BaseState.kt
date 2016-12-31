package com.adammcneilly.ourgovernment.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Represents a state.
 *
 * Created by adam.mcneilly on 12/26/16.
 */
@Root(strict=false)
open class BaseState() : BaseModel() {
    @field:Element var stateId: String = ""
    @field:Element var name: String = ""

    override fun toString(): String {
        return name
    }
}