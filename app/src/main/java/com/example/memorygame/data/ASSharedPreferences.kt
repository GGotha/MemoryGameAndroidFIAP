package com.example.memorygame.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ASSharedPreferences(private val context: Context) {

    private val gson = Gson()
    private val sharedPref: SharedPreferences
    private val name = "game_prefs"
    private val playerName = "player"
    private val ranking = "ranking"

    init {
        sharedPref = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    fun savePlayer(name: String) {
        val editor = sharedPref.edit()
        editor.putString(playerName, name)
        editor.apply()
    }

    fun getPlayer(): String? {
        return sharedPref.getString(playerName, "")
    }

    fun updateRanking(item: RankingItem) {
        val rankingList = calculateRankingPosition(getRanking())
        val rankingString = gson.toJson(rankingList)

        val editor = sharedPref.edit()
        editor.putString(ranking, rankingString)
        editor.apply()
    }

    fun getRanking(): ArrayList<RankingItem> {
        val rankingString = sharedPref.getString(ranking, null) ?: return arrayListOf()
        return gson.fromJson(rankingString, object : TypeToken<ArrayList<RankingItem>>() {}.type)
    }

    fun calculateRankingPosition(ranking: ArrayList<RankingItem>): ArrayList<RankingItem> {
        return  ranking
    }
}