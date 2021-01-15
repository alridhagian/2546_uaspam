package com.asy.footballinformation.presenter

import com.asy.footballinformation.api.ApiRepository
import com.asy.footballinformation.model.MatchResponse
import com.asy.footballinformation.view.SearchMatchView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchMatchPresenter (
    private val view: SearchMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getSearchMatch(teamsName: String?) {
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getSearchMatch(teamsName)).await(),
                MatchResponse::class.java
            )

                view.hideLoading()
                view.getMatch(data.searchMatchResponse.filter { it.strSport.equals("Soccer")})

        }

    }
}