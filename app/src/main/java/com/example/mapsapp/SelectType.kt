package com.example.mapsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_select_type.*
import kotlinx.android.synthetic.main.recommandation_item.view.*

class SelectType : AppCompatActivity() {

    lateinit var modelClass: ModelClass

    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_type)
        modelClass = intent.getSerializableExtra("model") as ModelClass

        driverName.text = modelClass.driver
        driverPhone.text = modelClass.phoneNumber
        ratings.text = "Ratings : ${modelClass.ratings}/5"
        totalCost.text = "Total Cost : ${modelClass.money}"
        type.setImageDrawable(
            getDrawable(
                when (modelClass.type) {
                    "Taxi" -> R.drawable.taxi
                    "Bus"->R.drawable.bus
                    "AutoRickshaw"->R.drawable.autorickshaw
                    else -> R.drawable.taxi
                }
            )
        )

        checkbox.setOnCheckedChangeListener { compoundButton, b ->
            if (b){
                modelClass.money = (modelClass.money.toInt() + 25).toString()
                totalCost.text = "Total Cost : ${modelClass.money}"
                carSanitizedDelivery.isVisible = true
            }else{
                modelClass.money = (modelClass.money.toInt() - 25).toString()
                totalCost.text = "Total Cost : ${modelClass.money}"
                carSanitizedDelivery.isVisible = false
            }
        }

        add.setOnClickListener {
            sanitized.text = (++count).toString()
            timesSanitized.text = "Sanitization : ${(count * 20)} "
            timesSanitized.isVisible = true
            modelClass.money = (modelClass.money.toInt() + 20).toString()
            totalCost.text = "Total Cost : ${modelClass.money}"
        }

        remove.setOnClickListener {
            timesSanitized.isVisible = count>0
            if (count>0){
                sanitized.text = (--count).toString()
                timesSanitized.isVisible = count>0
                timesSanitized.text = "Sanitization : ${(count * 20)} "
                modelClass.money = (modelClass.money.toInt() - 20).toString()
                totalCost.text = "Total Cost : ${modelClass.money}"
            }
        }

        checkout.setOnClickListener {
            startActivity(Intent(applicationContext, ReviewActivity::class.java))
        }

    }
}