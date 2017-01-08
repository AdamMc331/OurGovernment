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

        electionsService = retrofit.create(ElectionsService::class.java)
    }

    fun setApiMode(apiMode: MockInterceptor.APIMode) {
        mockInterceptor.apiMode = apiMode
    }

    fun registerMockResponse(path: String, model: MockableModel) {
        mockInterceptor.mockResponses.put(path, model)
    }

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