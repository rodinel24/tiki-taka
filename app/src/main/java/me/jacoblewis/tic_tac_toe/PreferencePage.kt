package com.example.tick

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PreferencePage: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.preference_page)

                var single = findViewById<Button>(R.id.btn_singleplayer)
                var multi = findViewById<Button>(R.id.btn_multiplayer)

                single.setOnClickListener
                {
                    val intent = Intent(this@PreferencePage, SinglePlayer::class.java)
                    startActivity(intent
                            finish()
                }
                multi.setOnClickListener
                {
                    val intent = Intent(this@PreferencePage, Multiplayer::class.java)
                    startActivity(intent
                            finish()
                }

            }
        }

    }
}