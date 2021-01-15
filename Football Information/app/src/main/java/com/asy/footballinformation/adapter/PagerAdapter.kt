package com.asy.footballinformation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.asy.footballinformation.fragment.PreviousMatchFragment
import com.asy.footballinformation.fragment.TeamsFragment
import com.asy.footballinformation.fragment.UpcomingMatchFragment

class PagerAdapter (fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                PreviousMatchFragment()
            }
            1 -> {
                UpcomingMatchFragment()
            }
            else ->  {
                return TeamsFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Last Match"
            1 -> "Next Match"
            else -> {
                return "Teams"
            }
        }
    }

}