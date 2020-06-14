package com.example.mapsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_view_pager.view.*


public class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val strings = listOf("Cab service with extra hygiene and sanitization facility","Bus update facility for conductors to update and keep a count of people in the bus and only allow limited people",
            "Whenever you're near to a person than 1 metre and a beep starts in the form of notification and helps maintain social distancing","Reward system for both drivers and travellers which encourage them for using this app more")

        val images = listOf(R.drawable.texi_illu, R.drawable.bus_illu, R.drawable.social_illu, R.drawable.reward_illu)

        fun bind(pos: Int){
            itemView.imageHolder.setImageDrawable(itemView.context.getDrawable(images[pos]))
            itemView.text.text = strings[pos]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_view_pager, parent, false))
    }

    override fun getItemCount() = 4

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

}