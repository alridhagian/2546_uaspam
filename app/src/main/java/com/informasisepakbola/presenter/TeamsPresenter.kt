package com.informasisepakbola.presenter

import com.asy.footballinformation.api.ApiRepository
import com.asy.footballinformation.model.TeamsResponse
import com.asy.footballinformation.view.*
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamsPresenter (
    private val view: TeamsView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getTeams(idHomeTeam: String?, idAwayTeam: String?) {

        GlobalScope.launch(Dispatchers.Main) {
            val homeTeams = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getTeamDetails(idHomeTeam)).await(),
                TeamsResponse::class.java
            )

            val awayTeams = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getTeamDetails(idAwayTeam)).await(),
                TeamsResponse::class.java
            )

                view.showTeams(homeTeams.teamsResponse, awayTeams.teamsResponse)

        }

    }
}


