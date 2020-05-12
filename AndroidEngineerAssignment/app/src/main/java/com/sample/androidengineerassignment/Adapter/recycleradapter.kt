package com.sample.androidengineerassignment.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.androidengineerassignment.R
import com.squareup.picasso.Picasso
import java.util.ArrayList

class recycleradapter(
    val context: Context,
    val itemList: ArrayList<String>,
     val imgurl: ArrayList<String>
) :
    RecyclerView.Adapter<recycleradapter.PostViewHolder>() {
    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.txtheading)
        val imgview: ImageView = view.findViewById(R.id.imgPost)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_single_row, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val text = itemList[position]
        holder.textView.text = text.toString()
        Picasso.get().load(imgurl[position])
            .resize(280, 180).into(holder.imgview)
    }
}