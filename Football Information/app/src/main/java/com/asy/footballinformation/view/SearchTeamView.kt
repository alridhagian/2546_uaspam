package com.asy.footballinformation.view

import com.asy.footballinformation.model.Teams

interface SearchTeamView {
    fun showLoading()
    fun hideLoading()
    fun getSearchTeam(data: List<Teams>)
}