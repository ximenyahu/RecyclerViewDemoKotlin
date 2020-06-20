package com.example.recyclerviewdemokotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row.view.*

class MyAdapter(val context: Context, val arrayList: ArrayList<Model>) :
    RecyclerView.Adapter<MyAdapter.InnerHolder>() {
    class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(model: Model) {
            itemView.row_title.text = model.title
            itemView.row_description.text = model.des
            itemView.row_image_view.setImageResource(model.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return InnerHolder(v)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: InnerHolder, position: Int) {
        holder.bindItem(arrayList[position])
    }
}