package com.informasisepakbola.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.asy.footballinformation.R
import com.asy.footballinformation.activity.DetailLigaActivity
import com.asy.footballinformation.activity.DetailMatchActivity
import com.asy.footballinformation.adapter.UpcomingMatchAdapter
import com.asy.footballinformation.api.ApiRepository
import com.asy.footballinformation.model.Match
import com.asy.footballinformation.presenter.UpcomingPresenter
import com.asy.footballinformation.view.UpcomingMatchView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_list_upcomingleague.*
import org.jetbrains.anko.startActivity

/**
 * A simple [Fragment] subclass.
 */
class UpcomingMatchFragment : Fragment(), UpcomingMatchView {

    lateinit var presenter: UpcomingPresenter
    private var match: MutableList<Match> = mutableListOf()
    private lateinit var adapter: UpcomingMatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_upcomingleague, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = UpcomingPresenter(this, apiRepository, gson)

        presenter.getUpcomingMatch(DetailLigaActivity.idLeague)

        adapter = UpcomingMatchAdapter(context, match) {
            context?.startActivity<DetailMatchActivity>("id" to "${it.idEvent}",
                DetailMatchActivity.ARGS_ID_AWAY to "${it.idAwayTeam}",
                DetailMatchActivity.ARGS_ID_HOME to "${it.idHomeTeam}")
        }

        rvUpcomingMatch.layoutManager = LinearLayoutManager(context)
        rvUpcomingMatch.adapter = adapter
    }

    override fun showUpcomingMatch(data: List<Match>) {
        match.clear()
        match.addAll(data)
        adapter.notifyDataSetChanged()
    }

}
