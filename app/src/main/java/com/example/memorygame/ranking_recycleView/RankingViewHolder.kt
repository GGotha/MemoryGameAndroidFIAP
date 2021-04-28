package com.example.memorygame.ranking_recycleView

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.memorygame.R

class RankingViewHolder (val row: View) : RecyclerView.ViewHolder(row) {
    val position: TextView = row.findViewById(R.id.position)
//    val player: TextView = row.findViewById(R.id.player)
//    val score: TextView = row.findViewById(R.id.score)
}