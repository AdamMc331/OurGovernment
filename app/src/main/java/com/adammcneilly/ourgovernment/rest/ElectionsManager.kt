package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.Election
import com.adammcneilly.ourgovernment.year
import retrofit2.Call
import java.util.*

/**
 * Handles all Elections calls.
 * TODO: Write tests.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
class ElectionsManager : BaseManager() {
    val electionsService: ElectionsService = retrofit.create(ElectionsService::class.java)

    fun getElection(electionId: String): Call<Election> {
        return electionsService.getElection(electionId)
    }

    fun getElectionByYearState(year: String, stateId: String): Call<Election> {
        return electionsService.getElectionByYearState(year, stateId)
    }

    fun getElectionByZip(zip5: String): Call<Election> {
        return getElectionByZip(zip5, "", Date().year().toString())
    }

    fun getElectionByZip(zip5: String, zip4: String, year: String): Call<Election> {
        return electionsService.getElectionByZip(zip5, zip4, year)
    }
}