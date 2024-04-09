package com.example.handbook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MyAdapter (listArray:ArrayList<ListItem>, context: Context): RecyclerView.Adapter<MyAdapter.ViewHolder>(){
    var listArrayR = listArray
    var contextR = context

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvContent = view.findViewById<TextView>(R.id.tvContent)
        val imItem = view.findViewById<ImageView>(R.id.imItem)

        fun bind(listItem: ListItem, context: Context){
            tvTitle.text = listItem.titleText
            tvContent.text = listItem.contentText
            imItem.setImageResource(listItem.image_id)
            itemView.setOnClickListener(){
                Toast.makeText(context, "нажат ${tvTitle.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val inflater = LayoutInflater.from(contextR)
        return ViewHolder(inflater.inflate(R.layout.item_layuot, parent, false))
    }

    override fun getItemCount(): Int {
       return listArrayR.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var listItem = listArrayR.get(position)
        holder.bind(listItem, contextR)
    }

    fun updateAdapter(listArray: List<ListItem>){
     listArrayR.clear()
     listArrayR.addAll(listArray)
        notifyDataSetChanged()
    }
}