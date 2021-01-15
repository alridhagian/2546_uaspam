package com.informasisepakbola.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.asy.footballinformation.R
import com.asy.footballinformation.activity.DetailMatchActivity
import com.asy.footballinformation.adapter.FavoriteMatchAdapter
import com.asy.footballinformation.database.database
import com.asy.footballinformation.model.FavoriteMatchField
import kotlinx.android.synthetic.main.fragment_favorite_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.*
import org.jetbrains.anko.startActivity


/**
 * A simple [Fragment] subclass.
 */
class FavoriteMatchFragment : Fragment() {

    private var favoriteMatchFields: MutableList<FavoriteMatchField> = mutableListOf()
    private lateinit var adapter: FavoriteMatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteMatchAdapter(favoriteMatchFields, context) {
            context?.startActivity<DetailMatchActivity>("id" to "${it.idEvent}",
                DetailMatchActivity.ARGS_ID_AWAY to "${it.idAwayTeam}",
                DetailMatchActivity.ARGS_ID_HOME to "${it.idHomeTeam}")
        }
        rvFavoriteMatch.layoutManager = LinearLayoutManager(context)
        rvFavoriteMatch.adapter = adapter
        showFavorite()
    }

    private fun  showFavorite() {
        favoriteMatchFields.clear()
        context?.database?.use {
            val result = select(FavoriteMatchField.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<FavoriteMatchField>())
            favoriteMatchFields.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }


}
