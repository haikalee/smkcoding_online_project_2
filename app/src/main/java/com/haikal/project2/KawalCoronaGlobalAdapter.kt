package com.haikal.project2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class KawalCoronaGlobalAdapter(val data: List<GlobalDataItem>): RecyclerView.Adapter<KawalCoronaGlobalAdapter.KawalCoronaHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KawalCoronaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.global_item, parent, false)
        return KawalCoronaHolder(view)
    }

    override fun onBindViewHolder(holder: KawalCoronaHolder, position: Int) {
        holder.country.text = data[position].attributes.countryRegion
        holder.confirmed.text = data[position].attributes.confirmed.toString()
        holder.recovery.text = data[position].attributes.confirmed.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class KawalCoronaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val country: TextView = itemView.findViewById(R.id.tv_global_country)
        val confirmed: TextView = itemView.findViewById(R.id.tv_global_confirmed)
        val recovery: TextView = itemView.findViewById(R.id.tv_global_recovered)
    }
}
