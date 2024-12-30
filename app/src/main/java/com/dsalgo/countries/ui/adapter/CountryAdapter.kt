package com.dsalgo.countries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsalgo.countries.data.model.Country
import com.dsalgo.countries.databinding.CountryListItemBinding

class CountryAdapter(private var countries: List<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    inner class CountryViewHolder(private val binding: CountryListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.countryLine1Left.text = "${country.name}, ${country.region}"
            binding.countryLine1Right.text = country.code
            binding.countryLine2.text = country.capital
        }

    }

    fun updateData(countries: List<Country>) {
        this.countries = countries
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = CountryListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CountryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }
}