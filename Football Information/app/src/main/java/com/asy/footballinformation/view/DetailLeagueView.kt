package com.asy.footballinformation.view

import com.asy.footballinformation.model.DetailLiga

interface DetailLeagueView {
    fun showDetails(data: List<DetailLiga?>?)
}