package com.adammcneilly.ourgovernment.models

import com.adammcneilly.ourgovernment.rest.BaseManager
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import retrofit2.Response
import java.io.IOException


/**
 * Represents an error object returned by the API.
 *
 * Created by adam.mcneilly on 12/31/16.
 */
@Root(strict=false)
open class Error {
    @field:Element var errorMessage = ""

    companion object {
        fun parseError(response: Response<*>?): Error {
            val api = BaseManager()
            val converter = api.retrofit.responseBodyConverter<Error>(Error::class.java, kotlin.arrayOfNulls<Annotation>(0))

            var error = Error()

            try {
                error = converter.convert(response?.errorBody()) as Error
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return error
        }
    }
}