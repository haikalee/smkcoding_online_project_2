package com.haikal.project2.rvadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haikal.project2.R
import com.haikal.project2.data.mathdro.GlobalDataItem

class CoronaGlobalAdapter(private val context: Context, val data: List<GlobalDataItem>): RecyclerView.Adapter<CoronaGlobalAdapter.KawalCoronaHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KawalCoronaHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.data_item, parent, false)
        return KawalCoronaHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: KawalCoronaHolder, position: Int) {
        holder.country.text = data[position].countryRegion
        holder.confirmed.text = data[position].confirmed.toString()
        holder.recovery.text = data[position].recovered.toString()
        holder.death.text = data[position].deaths.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class KawalCoronaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val country: TextView = itemView.findViewById(R.id.tv_name)
        val confirmed: TextView = itemView.findViewById(R.id.tv_confirmed)
        val recovery: TextView = itemView.findViewById(R.id.tv_recovered)
        val death: TextView = itemView.findViewById(R.id.tv_death)
    }
}
