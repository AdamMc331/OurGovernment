package com.adammcneilly.ourgovernment.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import com.adammcneilly.ourgovernment.R
import com.adammcneilly.ourgovernment.adapters.OfficialsAdapter
import com.adammcneilly.ourgovernment.models.CandidateList
import com.adammcneilly.ourgovernment.models.StateList
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

    var state: StateList.State? = null
        set(value) {
            field = value
            stateEditText?.setText(value?.name)
        }

    var officialsRecyclerView: RecyclerView? = null
    var stateEditText: EditText? = null
    var progressBar: ProgressBar? = null
    val officialsManager = OfficialsManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        state = arguments.getParcelable(ARG_STATE)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_state_officials, container, false)

        officialsRecyclerView = view?.findViewById(R.id.official_recycler_view) as? RecyclerView
        progressBar = view?.findViewById(R.id.progress_bar) as? ProgressBar
        stateEditText = view?.findViewById(R.id.state) as? EditText

        stateEditText?.setText(state?.name)

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

        officialsManager.getStatewideOfficials(state?.stateId.orEmpty())
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
        private val ARG_STATE = "state"

        fun newInstance(state: StateList.State?): StateOfficialsFragment {
            val args = Bundle()
            args.putParcelable(ARG_STATE, state)

            return newFragment(::StateOfficialsFragment, args)
        }
    }
}