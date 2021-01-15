package com.asy.footballinformation.view

import com.asy.footballinformation.model.Match

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun getMatch(data: List<Match>)
}