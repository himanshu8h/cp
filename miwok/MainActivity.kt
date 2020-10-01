package com.quantumwebgarden.miwok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        number_text_view.setOnClickListener {
            startActivity(Intent(this,NumberActivity::class.java))
        }
        family_text_view.setOnClickListener {
            startActivity(Intent(this,FamilyActivity::class.java))
        }
        color_text_view.setOnClickListener {
            startActivity(Intent(this,ColorActivity::class.java))
        }
        phrase_text_view.setOnClickListener {
            startActivity((Intent(this,PhraseActivity::class.java)))
        }
    }
}