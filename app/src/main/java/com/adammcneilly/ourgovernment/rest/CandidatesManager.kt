package com.adammcneilly.ourgovernment.rest

import com.adammcneilly.ourgovernment.models.CandidateList
import com.adammcneilly.ourgovernment.utils.year
import rx.Observable
import java.util.*

/**
 * Handles all Candidate calls.
 * TODO: Write tests.
 *
 * Created by adam.mcneilly on 1/8/17.
 */
class CandidatesManager : BaseManager() {
    val candidatesService: CandidatesService = retrofit.create(CandidatesService::class.java)

    fun getByOfficeState(officeId: String, stateId: String): Observable<CandidateList> {
        return getByOfficeState(officeId, stateId, Date().year().toString())
    }

    fun getByOfficeState(officeId: String, stateId: String, electionYear: String): Observable<CandidateList> {
        return candidatesService.getByOfficeState(officeId, stateId, electionYear)
    }

    fun getByOfficeTypeState(officeTypeId: String, stateId: String): Observable<CandidateList> {
        return getByOfficeTypeState(officeTypeId, stateId, Date().year().toString())
    }

    fun getByOfficeTypeState(officeTypeId: String, stateId: String, electionYear: String): Observable<CandidateList> {
        return candidatesService.getByOfficeTypeState(officeTypeId, stateId, electionYear)
    }

    fun getByLastName(lastName: String): Observable<CandidateList> {
        return getByLastName(lastName, Date().year().toString())
    }

    fun getByLastName(lastName: String, electionYear: String): Observable<CandidateList> {
        return candidatesService.getByLastName(lastName, electionYear)
    }

    fun getByElection(electionId: String): Observable<CandidateList> {
        return getByElection(electionId)
    }

    fun getByDistrict(districtId: String): Observable<CandidateList> {
        return getByDistrict(districtId, Date().year().toString())
    }

    fun getByDistrict(districtId: String, electionYear: String): Observable<CandidateList> {
        return candidatesService.getByDistrict(districtId, electionYear)
    }

    fun getByZip(zip5: String): Observable<CandidateList> {
        return getByZip(zip5, Date().year().toString(), "")
    }

    fun getByZip(zip5: String, electionYear: String, zip4: String): Observable<CandidateList> {
        return candidatesService.getByZip(zip5, electionYear, zip4)
    }
}