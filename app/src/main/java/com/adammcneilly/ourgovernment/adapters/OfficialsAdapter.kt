package com.adammcneilly.ourgovernment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.adammcneilly.CoreRecyclerViewAdapter
import com.adammcneilly.CoreViewHolder
import com.adammcneilly.ourgovernment.R
import com.adammcneilly.ourgovernment.models.CandidateList

/**
 * Displays a list of officials.
 *
 * Created by adam.mcneilly on 1/13/17.
 */
class OfficialsAdapter : CoreRecyclerViewAdapter<CandidateList.Candidate, OfficialsAdapter.OfficialViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): OfficialViewHolder {
        return OfficialViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.list_item_official, parent, false))
    }

    class OfficialViewHolder(view: View) : CoreViewHolder<CandidateList.Candidate>(view) {
        val officialName = view.findViewById(R.id.official_name) as? TextView
        val officialTitle = view.findViewById(R.id.official_title) as? TextView

        override fun bindItem(item: CandidateList.Candidate?) {
            officialName?.text = item?.fullName
            officialTitle?.text = item?.title
        }
    }
}