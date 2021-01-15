package com.asy.footballinformation.view

import com.asy.footballinformation.model.DetailMatch

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showDetailMatch(data: List<DetailMatch?>?)
}