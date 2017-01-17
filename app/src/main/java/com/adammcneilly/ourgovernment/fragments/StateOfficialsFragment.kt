package com.adammcneilly.ourgovernment.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.adammcneilly.ourgovernment.R
import com.adammcneilly.ourgovernment.adapters.OfficialsAdapter
import com.adammcneilly.ourgovernment.models.CandidateList
import com.adammcneilly.ourgovernment.rest.OfficialsManager
import com.adammcneilly.ourgovernment.utils.newFragment
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber

/**
 * Fragment that displays a list of state officials for a.
 *
 * Created by adam.mcneilly on 1/13/17.
 */
class StateOfficialsFragment : BaseFragment() {

    var stateId: String = ""
    var officialsRecyclerView: RecyclerView? = null
    var progressBar: ProgressBar? = null
    val officialsManager = OfficialsManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        stateId = arguments.getString(ARG_STATE_ID, "")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_state_officials, container, false)

        officialsRecyclerView = view?.findViewById(R.id.official_recycler_view) as? RecyclerView
        progressBar = view?.findViewById(R.id.progress_bar) as? ProgressBar

        // Setup recycler view
        val adapter = OfficialsAdapter()
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        officialsRecyclerView?.layoutManager = layoutManager
        officialsRecyclerView?.adapter = adapter

        getOfficials()

        return view
    }

    private fun getOfficials() {
        progressBar?.visibility = View.VISIBLE

        officialsManager.getStatewideOfficials(stateId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<CandidateList>() {
                    override fun onNext(t: CandidateList?) {
                        t?.sortBy { it.officeId.toInt() }
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
        private val ARG_STATE_ID = "stateId"

        fun newInstance(stateId: String?): StateOfficialsFragment {
            val args = Bundle()
            args.putString(ARG_STATE_ID, stateId)

            return newFragment(::StateOfficialsFragment, args)
        }
    }
}