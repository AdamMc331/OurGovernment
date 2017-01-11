package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.BuildConfig
import com.adammcneilly.ourgovernment.interfaces.MockableModel
import com.adammcneilly.ourgovernment.models.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * API calls run through here for VoteSmart.
 *
 * Created by adam.mcneilly on 12/26/16.
 */
open class BaseManager {
    private val loggingInterceptor = HttpLoggingInterceptor()
    private val mockInterceptor = MockInterceptor()
    val gson: Gson
    val retrofit: Retrofit

    init {
        if (BuildConfig.DEBUG) loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
                .addInterceptor(mockInterceptor)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(ApiKeyInterceptor())
                .build()

        gson = GsonBuilder()
                .registerTypeAdapter(OfficeBranchList::class.java, OfficeBranchList.OfficeBranchListDeserializer())
                .registerTypeAdapter(OfficeLevelList::class.java, OfficeLevelList.OfficeLevelListDeserializer())
                .registerTypeAdapter(OfficeTypeList::class.java, OfficeTypeList.OfficeTypeListDeserializer())
                .registerTypeAdapter(CandidateList::class.java, CandidateList.CandidateListDeserializer())
                .registerTypeAdapter(CandidateBio::class.java, CandidateBio.CandidateBioDeserializer())
                .registerTypeAdapter(CountyList::class.java, CountyList.CountyListDeserializer())
                .registerTypeAdapter(StateList::class.java, StateList.StateListDeserializer())
                .registerTypeAdapter(CityList::class.java, CityList.CityListDeserializer())
                .registerTypeAdapter(State::class.java, State.StateDeserializer())
                .create()

        retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://api.votesmart.org/")
                .client(client)
                .build()
    }

    fun setApiMode(apiMode: MockInterceptor.APIMode) {
        mockInterceptor.apiMode = apiMode
    }

    fun registerMockResponse(path: String, model: MockableModel) {
        mockInterceptor.mockResponses.put(path, model)
    }
}