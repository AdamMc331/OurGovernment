package com.adammcneilly.ourgovernment.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import com.adammcneilly.ourgovernment.adapters.OfficialsAdapter
import com.adammcneilly.ourgovernment.R
import com.adammcneilly.ourgovernment.models.CandidateList
import com.adammcneilly.ourgovernment.rest.LocalManager
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber

/**
 * Fragment that displays a list of local officials for a county.
 *
 * Created by adam.mcneilly on 1/13/17.
 */
class LocalOfficialsFragment : Fragment() {

    enum class LocalType {
        COUNTY,
        CITY
    }

    var localId: String = ""
    var localType: LocalType = LocalType.COUNTY
    var localEditText: EditText? = null
    var officialsRecyclerView: RecyclerView? = null
    var progressBar: ProgressBar? = null
    val localManager = LocalManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        localId = arguments.getString(ARG_LOCAL_ID, "")
        localType = arguments.getSerializable(ARG_LOCAL_TYPE) as LocalType
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_local_officials, container, false)

        localEditText = view?.findViewById(R.id.local_edit_text) as? EditText
        officialsRecyclerView = view?.findViewById(R.id.official_recycler_view) as? RecyclerView
        progressBar = view?.findViewById(R.id.progress_bar) as? ProgressBar

        // Setup recycler view
        val adapter = OfficialsAdapter()
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        officialsRecyclerView?.layoutManager = layoutManager
        officialsRecyclerView?.adapter = adapter

        // Set hint
        when (localType) {
            LocalType.COUNTY -> localEditText?.setHint(R.string.county)
            LocalType.CITY -> localEditText?.setHint(R.string.city)
        }

        getOfficials()

        return view
    }

    private fun getOfficials() {
        progressBar?.visibility = View.VISIBLE

        localManager.getLocalOfficials(localId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<CandidateList>() {
                    override fun onNext(t: CandidateList?) {
                        (officialsRecyclerView?.adapter as? OfficialsAdapter)?.swapItems(t)
                    }

                    override fun onCompleted() {
                        progressBar?.visibility = View.GONE
                    }

                    override fun onError(e: Throwable?) {
                        Timber.e(e)
                    }
                })
    }

    companion object {
        private val ARG_LOCAL_ID = "localId"
        private val ARG_LOCAL_TYPE = "localType"

        fun newInstance(localId: String, localType: LocalType): LocalOfficialsFragment {
            val args = Bundle()
            args.putString(ARG_LOCAL_ID, localId)
            args.putSerializable(ARG_LOCAL_TYPE, localType)

            val fragment = LocalOfficialsFragment()
            fragment.arguments = args

            return fragment
        }
    }
}