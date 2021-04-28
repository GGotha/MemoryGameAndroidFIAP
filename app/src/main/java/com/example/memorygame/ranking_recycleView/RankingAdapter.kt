package com.example.memorygame.ranking_recycleView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.memorygame.R
import com.example.memorygame.data.RankingItem

class RankingAdapter(val data: ArrayList<RankingItem>) : RecyclerView.Adapter<RankingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.ranking_item_view, parent, false)
        return RankingViewHolder(layout)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {

    }

}