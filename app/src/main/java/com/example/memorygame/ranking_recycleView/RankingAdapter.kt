package com.example.memorygame.ranking_recycleView

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
import com.example.memorygame.R


class RankingAdapter(val data: ArrayList<RankingItem>) : RecyclerView.Adapter<RankingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.ranking_item_view, parent, false)
        return RankingViewHolder(layout)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        holder.position.text = "${data[position].position.toString()}°"
        holder.player.text = data[position].player
        holder.score.text = "${data[position].score} pts"
    }

}