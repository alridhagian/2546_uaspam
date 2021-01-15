package com.asy.footballinformation.view

import com.asy.footballinformation.model.DetailTeam


interface DetailTeamView{
    fun showTeamDetail(data: List<DetailTeam>)
}