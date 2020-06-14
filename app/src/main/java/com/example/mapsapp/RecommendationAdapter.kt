package com.example.mapsapp

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recommandation_item.view.*

class RecommendationAdapter :
    ListAdapter<ModelClass, RecommendationAdapter.ViewHolder>(DiffUtils()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recommandation_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: ModelClass) {
            with(itemView) {
                driverTv.text = model.driver
                if (model.ratings.toInt() > 3)
                    driverRating.setTextColor(Color.parseColor("#00ff00"))
                else
                    driverRating.setTextColor(Color.parseColor("#ff0000"))
                driverRating.text = "${model.ratings}/5"
                phoneNumber.text = model.phoneNumber
                sanitized.text = "${model.sanitized} times sanitized"
                if (model.type=="Bus"){
                    sanitized.text = "${(1..5).random()} availability"
                }
                travelled.text = "${model.number}KM"
                cost.text = "$${model.money}"
                setOnClickListener {
                    val intent = Intent(context, SelectType::class.java)
                    intent.putExtra("model", model)
                    context.startActivity(intent)
                }
            }
        }
    }

    class DiffUtils : DiffUtil.ItemCallback<ModelClass>() {
        override fun areItemsTheSame(oldItem: ModelClass, newItem: ModelClass): Boolean {
            return oldItem.driver == newItem.driver
        }

        override fun areContentsTheSame(oldItem: ModelClass, newItem: ModelClass): Boolean {
            return oldItem.number == newItem.number
        }

    }

}