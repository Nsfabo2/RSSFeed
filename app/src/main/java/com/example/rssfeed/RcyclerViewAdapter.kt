package com.example.rssfeed

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RcyclerViewAdapter (private val Q: List<Questions>) : RecyclerView.Adapter<RcyclerViewAdapter.ItemViewHolder>(){
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row
                ,parent
                ,false
            )
        )

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val question = Q[position]

        holder.itemView.apply {
            PublisherTV.text = question.published
            QTV.text = question.title

                    holder.itemView.context.startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(question.id)))

            }
        }

    override fun getItemCount(): Int = Q.size
}