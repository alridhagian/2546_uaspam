package com.informasisepakbola.presenter

import com.asy.footballinformation.api.ApiRepository
import com.asy.footballinformation.model.MatchResponse
import com.asy.footballinformation.view.UpcomingMatchView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpcomingPresenter (
    private val view: UpcomingMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getUpcomingMatch(idLeague: String?) {
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                .doRequest(ApiRepository.TheSportDBApi.getUpcomingMatch(idLeague)).await(),
                MatchResponse::class.java
            )

                view.showUpcomingMatch(data.matchResponse)

        }

    }
}

