package com.example.memorygame

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.memorygame.data.ASSharedPreferences
import com.example.memorygame.databinding.FragmentHomeBinding
import com.example.memorygame.databinding.FragmentStartBinding
import kotlin.system.exitProcess


class HomeFragment : Fragment() {
    private lateinit var sharedPrefs: ASSharedPreferences
    private lateinit var bindings: FragmentHomeBinding
    private lateinit var player: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedPrefs = ASSharedPreferences(context)
        player = sharedPrefs.getPlayer().toString()


    }





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        //arguments?.let {
        //    param1 = it.getString(ARG_PARAM1)
        //    param2 = it.getString(ARG_PARAM2)
        //}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindings = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)


        return bindings.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindings.inputPlayer.setText(player)


        super.onViewCreated(view, savedInstanceState)
        bindings.next.setOnClickListener {
            val playerName = bindings.inputPlayer.text.toString()
            savePlayerName(playerName)
            val action = HomeFragmentDirections.actionHomeFragmentToStartFragment(playerName)//
            view.findNavController().navigate(action)
        }

        bindings.ranking.setOnClickListener {
            view.findNavController().navigate(R.id.action_startFragment_to_rankingFragment)
        }
    }

    private fun savePlayerName(name: String) {
        if (name != player) {
            sharedPrefs.savePlayer(name)
        }
    }


}