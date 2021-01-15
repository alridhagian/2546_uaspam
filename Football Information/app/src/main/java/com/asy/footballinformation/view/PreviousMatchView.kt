package com.asy.footballinformation.view

import com.asy.footballinformation.model.Match

interface PreviousMatchView {
    fun showLoading()
    fun hideLoading()
    fun showPreviousMatch(data: List<Match>)
}