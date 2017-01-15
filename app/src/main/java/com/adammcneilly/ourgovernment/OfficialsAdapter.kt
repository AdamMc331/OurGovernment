package com.adammcneilly.ourgovernment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.adammcneilly.ourgovernment.models.CandidateList

/**
 * Displays a list of officials.
 *
 * Created by adam.mcneilly on 1/13/17.
 */
class OfficialsAdapter : RecyclerView.Adapter<OfficialsAdapter.OfficialViewHolder>() {

    var officials: CandidateList = CandidateList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): OfficialViewHolder {
        return OfficialViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.list_item_official, parent, false))
    }

    override fun onBindViewHolder(holder: OfficialViewHolder?, position: Int) {
        holder?.bindOfficial(officials[position])
    }

    override fun getItemCount(): Int {
        return officials.size
    }

    fun swapItems(candidateList: CandidateList?) {
        this.officials = candidateList ?: CandidateList()
        notifyDataSetChanged()
    }

    class OfficialViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val officialName = view.findViewById(R.id.official_name) as? TextView
        val officialTitle = view.findViewById(R.id.official_title) as? TextView

        fun bindOfficial(official: CandidateList.Candidate) {
            officialName?.text = official.fullName
            officialTitle?.text = official.title
        }
    }
}