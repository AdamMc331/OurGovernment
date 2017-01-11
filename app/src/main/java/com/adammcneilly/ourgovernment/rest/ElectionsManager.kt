package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.Election
import com.adammcneilly.ourgovernment.year
import rx.Observable
import java.util.*

/**
 * Handles all Elections calls.
 * TODO: Write tests.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
class ElectionsManager : BaseManager() {
    val electionsService: ElectionsService = retrofit.create(ElectionsService::class.java)

    fun getElection(electionId: String): Observable<Election> {
        return electionsService.getElection(electionId)
    }

    fun getElectionByYearState(year: String, stateId: String): Observable<Election> {
        return electionsService.getElectionByYearState(year, stateId)
    }

    fun getElectionByZip(zip5: String): Observable<Election> {
        return getElectionByZip(zip5, "", Date().year().toString())
    }

    fun getElectionByZip(zip5: String, zip4: String, year: String): Observable<Election> {
        return electionsService.getElectionByZip(zip5, zip4, year)
    }
}