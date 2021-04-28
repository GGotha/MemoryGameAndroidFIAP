package com.example.memorygame

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memorygame.data.ASSharedPreferences
import com.example.memorygame.data.RankingItem
import com.example.memorygame.ranking_recycleView.RankingAdapter


class RankingFragment : Fragment() {

    private lateinit var sharedPrefs: ASSharedPreferences

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedPrefs = ASSharedPreferences(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ranking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv: RecyclerView = view.findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager(activity)
        rv.adapter = RankingAdapter(MOCK)
    }

}

val MOCK = arrayListOf<RankingItem>(
    RankingItem("João", 1212, 1),
    RankingItem("Maria", 1111, 2),
    RankingItem("João", 735, 3),
    RankingItem("Chuck", 722, 4),
    RankingItem("Peter Pan", 716, 5),
    RankingItem("Peter Pan", 544, 6),
    RankingItem("Sofia", 543, 7),
    RankingItem("Alberto", 541, 8),
    RankingItem("Bart", 212, 9),
    RankingItem("Homer", 71, 10)
)