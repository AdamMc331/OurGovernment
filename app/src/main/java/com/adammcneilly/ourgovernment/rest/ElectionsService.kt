package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.Election
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Handles all `Elections` calls.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
interface ElectionsService {
    @GET("Election.getElection")
    fun getElection(@Query("electionId") electionId: String): Observable<Election>

    @GET("Election.getElectionByYearState")
    fun getElectionByYearState(
            @Query("year") year: String,
            @Query("stateId") stateId: String): Observable<Election>

    fun getElectionByZip(
            @Query("zip5") zip5: String,
            @Query("zip4") zip4: String,
            @Query("year") year: String): Observable<Election>
}