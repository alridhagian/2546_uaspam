package com.asy.footballinformation.presenter

import com.asy.footballinformation.api.ApiRepository
import com.asy.footballinformation.model.SearchTeamResponse
import com.asy.footballinformation.view.SearchTeamView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchTeamPresenter (
    private val view: SearchTeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getSearchTeam(teamName: String?) {
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getSearchTeam(teamName)).await(),
                SearchTeamResponse::class.java
            )

            view.hideLoading()
            view.getSearchTeam(data.searchTeamResponse)

        }
    }
}