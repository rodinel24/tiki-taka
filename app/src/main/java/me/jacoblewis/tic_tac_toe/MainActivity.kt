package me.jacoblewis.tic_tac_toe


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val start = findViewById<Button>(R.id.btn_start)
        start.setOnClickListener{
            val intent = Intent(this@MainActivity, PreferencePage::class.java)
            startActivity(intent)
            finish()
        }

    }
}