package com.adammcneilly.ourgovernment.interfaces

/**
 * Interface that allows a model to have mock responses.
 *
 * Created by adam.mcneilly on 12/30/16.
 */
interface MockableModel {
    fun getSuccessJson(): List<String>
    fun getFailureJson(): List<String>
}