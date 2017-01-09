package com.adammcneilly.ourgovernment.models

import com.adammcneilly.ourgovernment.interfaces.MockableModel

/**
 * Base class for all models used in this project.
 *
 * Created by adam.mcneilly on 12/30/16.
 */
open class BaseModel : MockableModel {
    override fun getSuccessJson(): List<String> {
        return listOf("")
    }

    override fun getFailureJson(): List<String> {
        return listOf("")
    }
}