package com.asy.footballinformation.presenter

import com.asy.footballinformation.api.ApiRepository
import com.asy.footballinformation.model.LeagueResponse
import com.asy.footballinformation.view.DetailLeagueView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailLeaguePresenter (
    private val view: DetailLeagueView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getDetails(idLeague: String?) {

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getDetailLeague(idLeague)).await(),
                LeagueResponse::class.java
            )
                view.showDetails(data.league)
        }

    }
}