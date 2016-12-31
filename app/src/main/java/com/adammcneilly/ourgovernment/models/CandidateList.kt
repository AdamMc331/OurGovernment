package com.adammcneilly.ourgovernment.models

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.util.*

/**
 * Represents a list of candidates returned from the API
 *
 * Created by adam.mcneilly on 12/28/16.
 */
@Root(strict=false)
open class CandidateList() : BaseModel() {
    @field:ElementList(entry="candidate", inline=true) var list: ArrayList<Candidate> = ArrayList()
}