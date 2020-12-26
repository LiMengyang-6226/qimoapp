package com.example.qimo.ui.game

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.qimo.R
import kotlinx.android.synthetic.main.fragment_game.*
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.Exception

class GameFragment : Fragment() {
    val cardButtons: MutableList<Button> = mutableListOf<Button>()
    lateinit var adapter: CardAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rgame: CardMatchingGame? = loadData()
        if (rgame != null) {
            game = rgame
        } else {
            game = CardMatchingGame(24)
        }
        adapter = CardAdapter(game)
        recyclerView.adapter = adapter
        val configuration = resources.configuration
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.layoutManager = GridLayoutManager(activity, 6)
        } else {
            val gridLayoutManager = GridLayoutManager(activity, 4)
            recyclerView.layoutManager = gridLayoutManager
        }

        updateUI()
        adapter.setOnClickListener {
            game.chooseCardAtIndex(it)
            updateUI()
        }
        reset.setOnClickListener {
            game.reset()
            updateUI()
        }
    }

    private fun updateUI() {
        adapter.notifyDataSetChanged()
        score.text = String.format("%s%d", "Score", game.score)
        score.text = "Score:" + game.score
    }

    override fun onStop() {
        super.onStop()
        saveData()
    }

    fun saveData() {
        try {
            val output: FileOutputStream? =
                activity?.openFileOutput("data", AppCompatActivity.MODE_PRIVATE)
            val objectOutputStream = ObjectOutputStream(output)
            objectOutputStream.writeObject(game)
            objectOutputStream.close()
            output?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun loadData(): CardMatchingGame? {
        try {
            val input: FileInputStream? = activity?.openFileInput("data")
            val objectInputStream = ObjectInputStream(input)
            val game: CardMatchingGame = objectInputStream.readObject() as CardMatchingGame
            objectInputStream.close()
            input?.close()
            return game
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    companion object{
        lateinit var game: CardMatchingGame
        fun newInstance():GameFragment = GameFragment()
    }

}