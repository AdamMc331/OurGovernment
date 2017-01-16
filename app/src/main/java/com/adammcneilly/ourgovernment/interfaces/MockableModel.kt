package com.adammcneilly.ourgovernment.interfaces

/**
 * Interface that allows a model to have mock responses.
 *
 * Created by adam.mcneilly on 12/30/16.
 */
interface MockableModel {
    /**
     * @return a list of strings representing successful JSON from the server.
     */
    fun getSuccessJson(): List<String>

    /**
     * @return a list of strings representing failure JSON from the server.
     */
    fun getFailureJson(): List<String>
}