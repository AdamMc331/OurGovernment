package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.interfaces.MockableModel
import okhttp3.*
import java.net.HttpURLConnection
import java.util.*

/**
 * Intercepts retrofit requests and mocks their responses
 *
 * Created by adam.mcneilly on 12/30/16.
 */
open class MockInterceptor : Interceptor {
    enum class APIMode {
        LIVE,
        MOCK_SUCCESS,
        MOCK_ERROR
    }

    var apiMode = APIMode.LIVE

    val mockResponses: HashMap<String, MockableModel> = HashMap()

    override fun intercept(chain: Interceptor.Chain?): Response {
        if (apiMode == APIMode.LIVE) return chain!!.proceed(chain.request())

        // Get Request URI and path
        val uri = chain!!.request().url().uri()
        val path = uri.path

        val model = mockResponses[path]

        // If we haven't mocked this model, skip it
        if (model == null) return chain.proceed(chain.request())
        else return getMockResponse(apiMode, chain, model)
    }

    private fun getMockResponse(apiMode: APIMode, chain: Interceptor.Chain, model: MockableModel): Response {
        var httpCode: Int = HttpURLConnection.HTTP_OK
        var body: String = ""

        when (apiMode) {
            APIMode.MOCK_SUCCESS -> {
                body = model.getSuccessXml().first()
                httpCode = HttpURLConnection.HTTP_OK
            }
            APIMode.MOCK_ERROR -> {
                body = model.getFailureXml().first()
                httpCode = HttpURLConnection.HTTP_NOT_FOUND
            }
        }

        return Response.Builder()
                .code(httpCode)
                .message(body)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), body.toByteArray()))
                .addHeader("content-type", "application/json")
                .build()
    }
}