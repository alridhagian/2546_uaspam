package com.asy.footballinformation.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.asy.footballinformation.R
import com.asy.footballinformation.activity.DetailLigaActivity
import com.asy.footballinformation.activity.DetailMatchActivity
import com.asy.footballinformation.adapter.PreviousMatchAdapter
import com.asy.footballinformation.api.ApiRepository
import com.asy.footballinformation.model.Match
import com.asy.footballinformation.presenter.PreviousMatchPresenter
import com.asy.footballinformation.utils.invisible
import com.asy.footballinformation.utils.visible
import com.asy.footballinformation.view.PreviousMatchView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_list_previousmatch.*
import org.jetbrains.anko.startActivity

class PreviousMatchFragment : Fragment(), PreviousMatchView {



    lateinit var presenter: PreviousMatchPresenter
    private var match: MutableList<Match> = mutableListOf()
    private lateinit var adapter: PreviousMatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_previousmatch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = PreviousMatchPresenter(this, apiRepository, gson)
        presenter.getPreviousMatch(DetailLigaActivity.idLeague)

        adapter = PreviousMatchAdapter(context, match) {
            context?.startActivity<DetailMatchActivity>("id" to "${it.idEvent}",
                DetailMatchActivity.ARGS_ID_AWAY to "${it.idAwayTeam}",
                DetailMatchActivity.ARGS_ID_HOME to "${it.idHomeTeam}")
        }

        rvPreviousMatch.layoutManager = LinearLayoutManager(context)
        rvPreviousMatch.adapter = adapter
    }

    override fun showPreviousMatch(data: List<Match>) {
        match.clear()
        match.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        progressBarPrevious.visible()
    }

    override fun hideLoading() {
        progressBarPrevious.invisible()
    }
}
