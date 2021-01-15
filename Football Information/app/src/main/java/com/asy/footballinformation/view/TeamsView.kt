package com.asy.footballinformation.view

import com.asy.footballinformation.model.Teams

interface TeamsView {
    fun showTeams(homeTeams: List<Teams>, awayTeams: List<Teams>)
}
