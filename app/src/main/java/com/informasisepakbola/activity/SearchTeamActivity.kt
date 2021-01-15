package com.informasisepakbola.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.asy.footballinformation.R
import com.asy.footballinformation.adapter.TeamSearchAdapter
import com.asy.footballinformation.api.ApiRepository
import com.asy.footballinformation.model.Teams
import com.asy.footballinformation.presenter.SearchTeamPresenter
import com.asy.footballinformation.utils.invisible
import com.asy.footballinformation.utils.visible
import com.asy.footballinformation.view.SearchTeamView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search_team.*

class SearchTeamActivity : AppCompatActivity(), SearchTeamView {


    private lateinit var searchPresenter: SearchTeamPresenter
    private lateinit var adapter: TeamSearchAdapter
    private var teams: MutableList<Teams> = mutableListOf()

    companion object {
        const val ARGS_QUERY = "ARGS_QUERY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)

        val query = intent.getStringExtra(ARGS_QUERY)
        val actionBar = supportActionBar
        actionBar?.title = "Result for : $query"
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val apiRepository = ApiRepository()
        val gson = Gson()

        searchPresenter = SearchTeamPresenter(this, apiRepository, gson)
        searchPresenter.getSearchTeam(query)

        adapter = TeamSearchAdapter(teams, this)
        rvSearchTeam.layoutManager = LinearLayoutManager(this)
        rvSearchTeam.adapter = adapter
    }

    override fun showLoading() {
        progressBarSearchTeam.visible()
    }

    override fun hideLoading() {
        progressBarSearchTeam.invisible()
    }

    override fun getSearchTeam(data: List<Teams>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
