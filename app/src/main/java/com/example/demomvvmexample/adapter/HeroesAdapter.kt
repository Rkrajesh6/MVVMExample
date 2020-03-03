package com.example.demomvvmexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demomvvmexample.R
import com.example.demomvvmexample.model.Heroes
import com.squareup.picasso.Picasso
import okhttp3.internal.wait

/**
 * Created by rajeshkumar07 on 27-02-2020.
 */
class HeroesAdapter(context : Context,list : List<Heroes>) : RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>(){
    private val mContext = context
    private var mList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.row_recyclerview1,parent,false)
        return HeroesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val item = mList[position]
        //Glide.with(mContext).load(item.imageurl).into(holder.imageView)
        Picasso.get().load(item.imageurl).into(holder.imageView)
        holder.textViewName.text = item.name
        holder.textViewLink.text = item.bio
    }

    fun update(list : List<Heroes>){
        mList = list
        notifyDataSetChanged()
    }

    inner class HeroesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textViewLink: TextView = itemView.findViewById(R.id.textViewLink)
    }
}