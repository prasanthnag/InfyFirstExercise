package com.infy.newspoc.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.infy.newspoc.model.NewsDetails
import com.infy.newspoc.R

class NewsAdapter(
    private val newsDetails: ArrayList<NewsDetails>,
    private val context: Context
) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsDetails[position], context)
    }

    override fun getItemCount(): Int {
        return newsDetails.size
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(newsDetails: NewsDetails, context: Context) {
            val txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)
            val txtDescription = itemView.findViewById<TextView>(R.id.txtDescription)
            val imageCountry = itemView.findViewById<ImageView>(R.id.imgCountry)

            newsDetails.title?.let {
                txtTitle.text = it
            }
            newsDetails.description?.let {
                txtDescription.text = it
            }
            newsDetails.imageRef?.let {
                Glide.with(context).load(it).into(imageCountry)
            }

        }

    }

}