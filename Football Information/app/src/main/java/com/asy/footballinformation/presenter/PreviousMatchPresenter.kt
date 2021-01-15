package com.asy.footballinformation.presenter

import com.asy.footballinformation.api.ApiRepository
import com.asy.footballinformation.model.MatchResponse
import com.asy.footballinformation.view.PreviousMatchView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PreviousMatchPresenter (
    private val view: PreviousMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getPreviousMatch(idLeague: String?) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getPreviousMatch(idLeague)).await(),
                MatchResponse::class.java
            )
                view.hideLoading()
                view.showPreviousMatch(data.matchResponse)

        }

    }
}