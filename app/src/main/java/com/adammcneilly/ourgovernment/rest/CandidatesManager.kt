package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.CandidateList
import com.adammcneilly.ourgovernment.year
import retrofit2.Call
import java.util.*

/**
 * Handles all Candidate calls.
 * TODO: Write tests.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
class CandidatesManager : BaseManager() {
    val candidatesService: CandidatesService = retrofit.create(CandidatesService::class.java)

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
}