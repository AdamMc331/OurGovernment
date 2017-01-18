package com.adammcneilly.ourgovernment.models

import android.os.Parcel
import android.os.Parcelable
import com.adammcneilly.ourgovernment.interfaces.MockableModel
import com.adammcneilly.ourgovernment.utils.creator

/**
 * Base class for all models used in this project.
 *
 * Created by adam.mcneilly on 12/30/16.
 */
open class BaseModel : MockableModel, Parcelable {

    constructor()

    constructor(source: Parcel): this()

    override fun getSuccessJson(): List<String> {
        return listOf("")
    }

    override fun getFailureJson(): List<String> {
        return listOf("")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        val CREATOR = creator(::BaseModel)
    }
}