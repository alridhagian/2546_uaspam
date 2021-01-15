package com.informasisepakbola.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.asy.footballinformation.R
import com.asy.footballinformation.adapter.LigaAdapter
import com.asy.footballinformation.model.Liga
import kotlinx.android.synthetic.main.fragment_league.*

/**
 * A simple [Fragment] subclass.
 */
class LeagueFragment : Fragment() {

    private var liga: MutableList<Liga> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_league, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvLiga.layoutManager = GridLayoutManager(context, 2)
        rvLiga.adapter = LigaAdapter(context, liga)
        initData()
    }

    private fun initData() {
        val imgLiga = resources.obtainTypedArray(R.array.liga_img)
        val tvLiga = resources.getStringArray(R.array.liga_name)
        val tvId = resources.getStringArray(R.array.liga_id)
        liga.clear()

        for (i in tvLiga.indices) {
            liga.add(
                Liga(tvLiga[i],
                    imgLiga.getResourceId(i, 0),
                    tvId[i])
            )
        }

        imgLiga.recycle()

    }
}
