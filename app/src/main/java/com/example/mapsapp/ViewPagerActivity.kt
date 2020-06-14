package com.example.mapsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_pager.*
import kotlinx.android.synthetic.main.activity_view_pager.view.*

class ViewPagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        skip.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        next.setOnClickListener {
            if (viewPager.currentItem==3)
                startActivity(Intent(applicationContext, MainActivity::class.java))
            else
                viewPager.currentItem = viewPager.currentItem+1
        }

        viewPager.adapter = ViewPagerAdapter()
    }
}