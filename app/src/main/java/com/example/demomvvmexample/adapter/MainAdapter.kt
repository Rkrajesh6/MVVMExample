package com.example.demomvvmexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demomvvmexample.R
import com.example.demomvvmexample.model.Todo

/**
 * Created by rajeshkumar07 on 27-02-2020.
 */
class MainAdapter(private val context : Context, private var listItem : List<Todo>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private val mContext = context
    private var mList = listItem
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.row_recyclerview,parent,false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = mList[position]
        holder.userId.text = item.userId.toString()
        holder.id.text = item.id.toString()
        holder.title.text = item.title.toString()
        holder.completed.text = item.completed.toString()
    }

    public fun update(list : List<Todo>){
        mList = list
        notifyDataSetChanged()
    }

    inner class MainViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val userId = itemView.findViewById<TextView>(R.id.tvUserId)
        val id = itemView.findViewById<TextView>(R.id.tvId)
        val title = itemView.findViewById<TextView>(R.id.tvTitle)
        val completed = itemView.findViewById<TextView>(R.id.tvCompleted)
    }
}