package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.BuildConfig
import com.adammcneilly.ourgovernment.interfaces.MockableModel
import com.adammcneilly.ourgovernment.models.*
import com.adammcneilly.ourgovernment.year
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.*


/**
 * API calls run through here for VoteSmart.
 *
 * Created by adam.mcneilly on 12/26/16.
 */
open class VSApi {
    private val localService: LocalService
    private val candidateBioService: CandidateBioService
    private val candidatesService: CandidatesService
    private val electionsService: ElectionsService
    private val loggingInterceptor = HttpLoggingInterceptor()
    private val mockInterceptor = MockInterceptor()
    val retrofit: Retrofit

    init {
        if (BuildConfig.DEBUG) loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
                .addInterceptor(mockInterceptor)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(ApiKeyInterceptor())
                .build()

        val strategy = AnnotationStrategy()
        val serializer = Persister(strategy)

        retrofit = Retrofit.Builder()
                .addConverterFactory(SimpleXmlConverterFactory.create(serializer))
                .baseUrl("http://api.votesmart.org/")
                .client(client)
                .build()

        localService = retrofit.create(LocalService::class.java)
        candidateBioService = retrofit.create(CandidateBioService::class.java)
        candidatesService = retrofit.create(CandidatesService::class.java)
        electionsService = retrofit.create(ElectionsService::class.java)
    }

    fun setApiMode(apiMode: MockInterceptor.APIMode) {
        mockInterceptor.apiMode = apiMode
    }

    fun registerMockResponse(path: String, model: MockableModel) {
        mockInterceptor.mockResponses.put(path, model)
    }

    //region Local
    fun getCounties(stateId: String): Call<CountyList> {
        return localService.getCounties(stateId)
    }

    fun getCities(stateId: String): Call<CityList> {
        return localService.getCities(stateId)
    }

    fun getLocalOfficials(localId: String): Call<CandidateList> {
        return localService.getLocalOfficials(localId)
    }
    //endregion

    //region CandidateBio
    fun getBio(candidateId: String): Call<CandidateBio> {
        return candidateBioService.getBio(candidateId)
    }
    //endregion

    //region Candidates
    fun getByOfficeState(officeId: String, stateId: String): Call<CandidateList> {
        return getByOfficeState(officeId, stateId, Date().year().toString())
    }

    fun getByOfficeState(officeId: String, stateId: String, electionYear: String): Call<CandidateList> {
        return candidatesService.getByOfficeState(officeId, stateId, electionYear)
    }

    fun getByOfficeTypeState(officeTypeId: String, stateId: String): Call<CandidateList> {
        return getByOfficeTypeState(officeTypeId, stateId, Date().year().toString())
    }

    fun getByOfficeTypeState(officeTypeId: String, stateId: String, electionYear: String): Call<CandidateList> {
        return candidatesService.getByOfficeTypeState(officeTypeId, stateId, electionYear)
    }

    fun getByLastName(lastName: String): Call<CandidateList> {
        return getByLastName(lastName, Date().year().toString())
    }

    fun getByLastName(lastName: String, electionYear: String): Call<CandidateList> {
        return candidatesService.getByLastName(lastName, electionYear)
    }

    fun getByElection(electionId: String): Call<CandidateList> {
        return getByElection(electionId)
    }

    fun getByDistrict(districtId: String): Call<CandidateList> {
        return getByDistrict(districtId, Date().year().toString())
    }

    fun getByDistrict(districtId: String, electionYear: String): Call<CandidateList> {
        return candidatesService.getByDistrict(districtId, electionYear)
    }

    fun getByZip(zip5: String): Call<CandidateList> {
        return getByZip(zip5, Date().year().toString(), "")
    }

    fun getByZip(zip5: String, electionYear: String, zip4: String): Call<CandidateList> {
        return candidatesService.getByZip(zip5, electionYear, zip4)
    }
    //endregion

    //region Election
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
    //endregion
}