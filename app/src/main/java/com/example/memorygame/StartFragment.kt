package com.example.memorygame

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.memorygame.R.drawable.*
import kotlinx.android.synthetic.main.fragment_start.*
import java.util.concurrent.TimeUnit


class StartFragment : Fragment() {

    private val START_TIME_IN_MILLIS: Long = 600000
    private lateinit var buttons: List<ImageButton>
    private lateinit var cards: List<MemoryCard>
    private var indexOfSingleSelectedCard: Int? = null
    var cardsFound: Int = 0;
    private var userScore: Long = 100


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val chronometer: Chronometer = view.findViewById(R.id.chronometer)
        chronometer!!.setBase(SystemClock.elapsedRealtime());
        chronometer!!.setOnChronometerTickListener {this.onChronometerTick(chronometer)};
        chronometer!!.start();
        chronometer!!.setFormat("Tempo: %s");


        val images = mutableListOf(
            ic_alarm,
            ic_audio,
            ic_cart,
            ic_code,
            ic_money,
            ic_moon,
            ic_plane,
            ic_trash
        )
        images.addAll(images)
        images.shuffle()

        buttons = listOf(
            imageButton1,
            imageButton2,
            imageButton3,
            imageButton4,
            imageButton5,
            imageButton6,
            imageButton7,
            imageButton8,
            imageButton9,
            imageButton10,
            imageButton11,
            imageButton12,
            imageButton13,
            imageButton14,
            imageButton15,
            imageButton16
        )


        cards = buttons.indices.map { index ->
            MemoryCard(images[index])
        }

        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                Log.i(TAG, "tap")
                updateModels(index, chronometer)
                updateViews()

            }
        }


    }

    private fun onChronometerTick(chronometer: Chronometer) {
        val miliseconds: Long = SystemClock.elapsedRealtime() - chronometer.getBase()
        var seconds = TimeUnit.MILLISECONDS.toSeconds(miliseconds)

        if (seconds == 10L) {
            userScore = userScore - 20
            star5.setImageResource(ic_star_white)
        }else if (seconds == 20L) {
            userScore = userScore - 20
            star4.setImageResource(ic_star_white)
        }else if (seconds == 30L) {
            userScore = userScore - 20
            star3.setImageResource(ic_star_white)
        }else if (seconds == 40L) {
            userScore = userScore - 20
            star2.setImageResource(ic_star_white)
        }else if (seconds == 50L) {
            userScore = userScore - 20
            star1.setImageResource(ic_star_white)
        }
    }

    private fun updateViews() {
        cards.forEachIndexed { index, card ->
            val button = buttons[index]
            if (card.isMatched) {
                button.alpha = 0.1f
            }
            button.setImageResource(if (card.isFaceUp) card.identifier else ic_menu)
        }
    }

    private fun updateModels(position: Int, chronometer: Chronometer) {
        val card = cards[position]
        if (card.isFaceUp) {
            return
        }

        if (indexOfSingleSelectedCard == null) {
            restoreCards()
            indexOfSingleSelectedCard = position
        } else {
            checkForMatch(indexOfSingleSelectedCard!!, position, chronometer)
            indexOfSingleSelectedCard = null
        }
        card.isFaceUp = !card.isFaceUp
    }

    private fun restoreCards() {
        for (card in cards) {
            if (!card.isMatched) {
                card.isFaceUp = false
            }
        }
    }

    private fun checkForMatch(position1: Int, position2: Int, chronometer: Chronometer) {

        if (cards[position1].identifier == cards[position2].identifier) {
            Toast.makeText(requireActivity(), "Par encontrado!!", Toast.LENGTH_SHORT).show()
            cards[position1].isMatched = true
            cards[position2].isMatched = true

            cardsFound++;

            if (cardsFound == 8) {
                chronometer!!.stop();
                val builder = AlertDialog.Builder(requireActivity())
                builder.setTitle("Fim de jogo")
                builder.setMessage("Parabéns, você terminou o jogo")
                builder.show()

                findNavController().navigate(
                    R.id.startFragment,
                    arguments,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.startFragment, true)
                        .build()
                )
            }

        }
    }


}