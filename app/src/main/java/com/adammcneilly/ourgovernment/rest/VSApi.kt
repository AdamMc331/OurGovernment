package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.BuildConfig
import com.adammcneilly.ourgovernment.interfaces.MockableModel
import com.adammcneilly.ourgovernment.models.CountyList
import com.adammcneilly.ourgovernment.models.BaseStateList
import com.adammcneilly.ourgovernment.models.CityList
import com.adammcneilly.ourgovernment.models.State
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

/**
 * API calls run through here for VoteSmart.
 *
 * Created by adam.mcneilly on 12/26/16.
 */
class VSApi {
    private val VSApi: VSService
    private val loggingInterceptor = HttpLoggingInterceptor()
    private val mockInterceptor = MockInterceptor()

    init {
        if (BuildConfig.DEBUG) loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
                .addInterceptor(mockInterceptor)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(ApiKeyInterceptor())
                .build()

        val retrofit = Retrofit.Builder()
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .baseUrl("http://api.votesmart.org/")
                .client(client)
                .build()

        VSApi = retrofit.create(VSService::class.java)
    }

    fun setApiMode(apiMode: MockInterceptor.APIMode) {
        mockInterceptor.apiMode = apiMode
    }

    fun registerMockResponse(path: String, model: MockableModel) {
        mockInterceptor.mockResponses.put(path, model)
    }

    fun getStateIDs(): Call<BaseStateList> {
        return VSApi.getStateIDs()
    }

    fun getState(stateId: String): Call<State> {
        return VSApi.getState(stateId)
    }

    fun getCounties(stateId: String): Call<CountyList> {
        return VSApi.getCounties(stateId)
    }

    fun getCities(stateId: String): Call<CityList> {
        return VSApi.getCities(stateId)
    }
}