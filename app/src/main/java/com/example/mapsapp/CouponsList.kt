package com.example.mapsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_coupons_list.*

class CouponsList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupons_list)

        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        recyclerView.adapter = CouponsAdapter()
    }
}