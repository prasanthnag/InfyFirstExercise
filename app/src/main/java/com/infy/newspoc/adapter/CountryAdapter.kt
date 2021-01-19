package com.infy.infypoc.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.infy.infypoc.model.CountryDetails
import com.infy.newspoc.R

class CountryAdapter(
    private val countryDetails: ArrayList<CountryDetails>,
    private val context: Context
) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_sample, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countryDetails[position], context)
    }

    override fun getItemCount(): Int {
        return countryDetails.size
    }

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(countryDetails: CountryDetails, context: Context) {
            val txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)
            val txtDescription = itemView.findViewById<TextView>(R.id.txtDescription)
            val imageCountry = itemView.findViewById<ImageView>(R.id.imgCountry)

            countryDetails.title?.let {
                txtTitle.text = it
            }
            countryDetails.description?.let {
                txtDescription.text = it
            }
            countryDetails.imageRef?.let {
                Glide.with(context).load(it).into(imageCountry)
            }

        }

    }

}