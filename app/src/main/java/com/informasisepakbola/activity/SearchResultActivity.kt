package com.informasisepakbola.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.asy.footballinformation.R
import com.asy.footballinformation.adapter.ResultSearchAdapter
import com.asy.footballinformation.api.ApiRepository
import com.asy.footballinformation.model.Match
import com.asy.footballinformation.presenter.SearchMatchPresenter
import com.asy.footballinformation.utils.invisible
import com.asy.footballinformation.utils.visible
import com.asy.footballinformation.view.SearchMatchView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search_result.*

class SearchResultActivity : AppCompatActivity(), SearchMatchView {

    lateinit var searchPresenter: SearchMatchPresenter
    private lateinit var adapter: ResultSearchAdapter
    private var match: MutableList<Match> = mutableListOf()

    companion object {
        const val ARGS_QUERY = "ARGS_QUERY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        val query = intent.getStringExtra(ARGS_QUERY)
        val actionBar = supportActionBar
        actionBar?.title = "Result for : $query"
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val apiRepository = ApiRepository()
        val gson = Gson()
        searchPresenter = SearchMatchPresenter(this, apiRepository, gson)
        searchPresenter.getSearchMatch(query)

        adapter = ResultSearchAdapter(this, match)
        rvSearchMatch.layoutManager = LinearLayoutManager(this)
        rvSearchMatch.adapter = adapter
    }

    override fun showLoading() {
        progressBarSearch.visible()
    }

    override fun hideLoading() {
        progressBarSearch.invisible()
    }

    override fun getMatch(data: List<Match>) {
        match.clear()
        match.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
