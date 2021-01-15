package com.asy.footballinformation.view

import com.asy.footballinformation.model.Teams

interface ListTeamView {
    fun showListTeam(data: List<Teams>)
}