package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface into the VoteSmart API.

 * Created by adam.mcneilly on 12/26/16.
 */
interface VSService {
    //region State
    @GET("State.getStateIDs")
    fun getStateIDs(): Call<StateList>

    @GET("State.getState")
    fun getState(@Query("stateId") stateId: String): Call<State>
    //endregion

    //region Local
    @GET("Local.getCounties")
    fun getCounties(@Query("stateId") stateId: String): Call<CountyList>

    @GET("Local.getCities")
    fun getCities(@Query("stateId") stateId: String): Call<CityList>

    @GET("Local.getOfficials")
    fun getLocalOfficials(@Query("localId") localId: String): Call<CandidateList>
    //endregion

    //region CandidateBio
    @GET("Candidate.getBio")
    fun getBio(@Query("candidateId") candidateId: String): Call<CandidateBio>
    //endregion

    //region Candidates
    @GET("Candidates.getByOfficeState")
    fun getByOfficeState(
            @Query("officeId") officeId: String,
            @Query("stateId") stateId: String,
            @Query("electionYear") electionYear: String,
            @Query("stageId") stageId: String): Call<CandidateList>

    @GET("Candidates.getByZip")
    fun getByZip(
            @Query("zip5") zip5: String,
            @Query("electionYear") electionYear: String,
            @Query("zip4") zip4: String,
            @Query("stageId") stageId: String): Call<CandidateList>
    //endregion
}
