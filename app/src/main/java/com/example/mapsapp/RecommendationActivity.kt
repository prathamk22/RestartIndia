package com.example.mapsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import kotlinx.android.synthetic.main.activity_recommendation.*

class RecommendationActivity : AppCompatActivity() {

    lateinit var type: String
    val names = listOf("Arvind Singh","Manish Singh", "Tejpal Singh", "Gurvinder Singh","Rajat Singh","Prajjwal Kohli", "Pratham Khurana", "Pranav Singh",
        "Arvind Singh","Manish Singh", "Tejpal Singh", "Gurvinder Singh","Rajat Singh","Prajjwal Kohli", "Pratham Khurana", "Pranav Singh")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommendation)

        type = intent.getStringExtra("type")
        recommendation.layoutManager = LinearLayoutManager(applicationContext)
        val adapter = RecommendationAdapter()
        recommendation.adapter = adapter
        adapter.submitList(generateData())
    }

    fun generateData(): List<ModelClass>{
        val list = mutableListOf<ModelClass>()
        val end = (1..6).random()
        for (i in end..10){
            val model = ModelClass()
            model.money = (200..400).random().toString()
            model.type = type
            model.ratings = (0..5).random().toString()
            model.number = (20..40).random().toString()
            model.phoneNumber = (9000000000..9999999999).random().toString()
            model.driver = names[i-end]
            model.sanitized = (5..10).random().toString()
            list.add(model)
        }
        list.sortBy { modelClass ->modelClass.ratings  }
        list.reverse()
        return list
    }

}