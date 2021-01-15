package com.informasisepakbola.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.asy.footballinformation.R
import com.asy.footballinformation.activity.DetailTeamActivity
import com.asy.footballinformation.adapter.FavoriteTeamAdapter
import com.asy.footballinformation.database.database
import com.asy.footballinformation.model.FavoriteTeamField
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTeamFragment : Fragment() {

    private var favorite: MutableList<FavoriteTeamField> = mutableListOf()
    private lateinit var adapter: FavoriteTeamAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteTeamAdapter(favorite, context) {
            context?.startActivity<DetailTeamActivity>(DetailTeamActivity.ARGS_ID to "${it.idTeam}")
        }
        rvFavoriteTeam.layoutManager = LinearLayoutManager(context)
        rvFavoriteTeam.adapter = adapter
        showFavorite()
    }

    private fun showFavorite() {
        favorite.clear()
        context?.database?.use {
            val result = select(FavoriteTeamField.TABLE_FAVORITE)
            val favoriteTeamField = result.parseList(classParser<FavoriteTeamField>())
            favorite.addAll(favoriteTeamField)
            adapter.notifyDataSetChanged()
        }
    }

}
