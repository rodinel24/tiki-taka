package com.example.tick
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlin.math.round

class Multiplayer: AppCompatActivity(), View.OnClickListener {

    private val button: Array<Array<Button?>> = Array<Array<Button?>>(3) { arrayOfNulls<Button>(3) }
    private var player1_turn = true
    private var round_count = 0
    private var player1_Points = 0
    private var player2_Points = 0
    private lateinit var tv_player1: TextView
    private lateinit var tv_player2: TextView
    private var playerFirst =""
    private var playerSecond =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.multiplayer_activity)



        tv_player1 = findViewById(R.id.tv_player1)
        tv_player2 = findViewById(R.id.tv_player2)

        getPlayerName()
        for (i in 0..2) {
            for (j in 0..2) {
                val buttonId = "button_$i$j"
                val resId = resources.getIdentifier(buttonId, "id", packageName)

                button[i][j] = findViewById(resId)
                button[i][j]!!.setOnClickListener(this)


            }
        }
        val reset_btn = findViewById<Button>(R.id.button_reset)
        reset_btn.setOnClickListener { resetGame() }
    }

    private fun resetGame() {
        player1_Points = 0
        player2_Points = 0
        update_points_text()
        reset_Board()

    }

    private fun getPlayerName() {
        val infalter = LayoutInflater.from(this@MainActivity)
        val pl_View = infalter.inflate(R.layout.players_info, null)
        val player1_name = pl_View.findViewById<EditText>(R.id.first_player)
        val player2_name = pl_View.findViewById<EditText>(R.id.second_player)
        val pl_dialog = AlertDialog.Builder(this@MainActivity)
        pl_dialog.setView(pl_View)
        pl_dialog.setPositiveButton("Add"){dialog,_->
            if(player1_name.text.toString().isEmpty()
                && player2_name.text.toString().isEmpty())
            {
                playerFirst = "Player 1"
                playerSecond = "Player 2"

                tv_player1.text = "$playerFirst : 0"
                tv_player2.text = "$playerSecond : 0"

            }
            else
            {
                playerFirst = player1_name.text.toString()
                playerSecond = player2_name.text.toString()

                tv_player1.text = "$playerFirst : 0"
                tv_player2.text = "$playerSecond : 0"
            }
        }
        pl_dialog.setNeutralButton("Cancel"){
                dialog,_->
            playerFirst = "Player 1"
            playerSecond = "Player 2"

            tv_player1.text = "$playerFirst : 0"
            tv_player2.text = "$playerSecond : 0"
        }
        pl_dialog.create()
        pl_dialog.show()
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {


        if ((v as Button).text.toString() != "") {
            return
        }
        if (player1_turn) {
            (v as Button).text = "@drawable/ic_star"

        } else {
            (v as Button).text = "@drawable/ic_circle"

        }
        round_count++
        if (check_for_win()) {
            if (player1_turn) {
                player1_win()
            } else {
                player2_win()
            }
        } else if (round_count == 9) {
            draw()
        } else {
            player1_turn = !player1_turn
        }

    }

    private fun draw() {
        setTitle("DRAW!!!")
        AlertDialog.Builder(this).setMessage("DRAW!").setPositiveButton("Ok"){dialog,_-> reset_Board()}
            .setNeutralButton("Cancel"){dialog,_-> reset_Board()}
            .create().show()
    }

    private fun player1_win() {

        player1_Points++
        update_points_text()
        reset_Board()
    }
    private fun player2_win() {
        player2_Points++
        update_points_text()
        reset_Board()
    }
    private fun reset_Board() {
        for (i in 0..2)
        {
            for (j in 0..2)
            {
                button[i][j]!!.text = ""
            }
        }
        round_count = 0
        player1_turn = false
    }

    private fun update_points_text() {
        tv_player1.text = "$playerFirst: $player1_Points"
        tv_player2.text = "$playerSecond: $player2_Points"
    }



    private fun check_for_win(): Boolean {

        val field = Array(3){ arrayOfNulls<String>(3)}

        for(i in 0..2)
        {
            for(j in 0..2)
            {
                field[i][j] = button[i][j]!!.text.toString()
            }
        }


        for (i in 0..2)
        {
            if(field[i][0]== field[i][1] && field[i][0] == field[i][2] && field[i][0]!="")
            {
                return true
            }
        }
        for (i in 0..2)
        {
            if(field[0][i]== field[1][i] && field[0][i] == field[2][i] && field[0][i]!="")
            {
                return true
            }
        }
        if(field[0][0]== field[1][1] && field[0][0] == field[2][2] && field[0][0]!="")
        {
            return true
        }

        return if(field[0][2]== field[1][1] && field[0][2] == field[2][0] && field[0][2]!="")
        {true}
        else false
    }


    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt("Round count" , round_count)
        outState.putInt("Player 1 Points" , player1_Points)
        outState.putInt("Player 2 Points" , player2_Points)
        outState.putBoolean("Player 1 Turn" , player1_turn)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        round_count = savedInstanceState.getInt("Round count")
        player1_Points = savedInstanceState.getInt("Player 1 Points")
        player2_Points = savedInstanceState.getInt("Player 2 Points")
        player1_turn = savedInstanceState.getBoolean("Player 1 Turn")

    }
}














