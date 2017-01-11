package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.CandidateList
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Handles all `Candidates` calls.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
interface CandidatesService {
    @GET("Candidates.getByOfficeState")
    fun getByOfficeState(
            @Query("officeId") officeTypeId: String,
            @Query("stateId") stateId: String,
            @Query("electionYear") electionYear: String): Observable<CandidateList>

    @GET("Candidates.getByOfficeTypeState")
    fun getByOfficeTypeState(
            @Query("officeId") officeId: String,
            @Query("stateId") stateId: String,
            @Query("electionYear") electionYear: String): Observable<CandidateList>

    @GET("Candidates.getByLastName")
    fun getByLastName(
            @Query("lastName") lastName: String,
            @Query("electionYear") electionYear: String): Observable<CandidateList>

    @GET("Candidates.getByElection")
    fun getByElection(
            @Query("electionId") electionId: String): Observable<CandidateList>

    @GET("Candidates.getByDistrict")
    fun getByDistrict(
            @Query("districtId") districtId: String,
            @Query("electionYear") electionYear: String): Observable<CandidateList>

    @GET("Candidates.getByZip")
    fun getByZip(
            @Query("zip5") zip5: String,
            @Query("electionYear") electionYear: String,
            @Query("zip4") zip4: String): Observable<CandidateList>
}