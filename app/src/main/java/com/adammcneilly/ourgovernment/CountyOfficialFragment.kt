package com.adammcneilly.ourgovernment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
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
class CountyOfficialFragment : Fragment() {

    var localId: String = ""
    var officialRecyclerView: RecyclerView? = null
    var progressBar: ProgressBar? = null
    val localManager = LocalManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        localId = arguments.getString(ARG_LOCAL_ID, "")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_county_officials, container, false)

        officialRecyclerView = view?.findViewById(R.id.official_recycler_view) as? RecyclerView
        progressBar = view?.findViewById(R.id.progress_bar) as? ProgressBar

        // Setup recycler view
        val adapter = OfficialAdapter()
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        officialRecyclerView?.layoutManager = layoutManager
        officialRecyclerView?.adapter = adapter

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
                        (officialRecyclerView?.adapter as? OfficialAdapter)?.swapItems(t)
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

        fun newInstance(localId: String): CountyOfficialFragment {
            val args = Bundle()
            args.putString(ARG_LOCAL_ID, localId)

            val fragment = CountyOfficialFragment()
            fragment.arguments = args

            return fragment
        }
    }
}