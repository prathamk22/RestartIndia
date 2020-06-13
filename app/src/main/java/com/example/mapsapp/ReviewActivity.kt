package com.example.mapsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import kotlinx.android.synthetic.main.activity_review.*

class ReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        submit.setOnClickListener {
            if (review.text.length > 10)
                finish()
        }
    }
}