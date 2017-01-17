package com.adammcneilly.ourgovernment.models

/**
 * Holds user specific info like state, county, city, and zip.
 *
 * Created by adam.mcneilly on 1/16/17.
 */
class User {
    var state: StateList.State? = null
    var county: CountyList.County? = null
    var city: CityList.City? = null
    var zipCode: String? = null
}