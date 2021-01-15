package com.asy.footballinformation.view

import com.asy.footballinformation.model.Match


interface UpcomingMatchView {
    fun showUpcomingMatch(data: List<Match>)
}