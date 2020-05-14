package com.haikal.project2.rvadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haikal.project2.R
import com.haikal.project2.data.lokal.provinsi.ProvinsiData
import com.haikal.project2.data.lokal.provinsi.ProvinsiDataItem

class KawalCoronaProvinsiAdapter(private val provinsiData: List<ProvinsiDataItem>): RecyclerView.Adapter<KawalCoronaProvinsiAdapter.KawalCoronaProvinsiHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KawalCoronaProvinsiHolder {
        return KawalCoronaProvinsiHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_penyebaran, parent, false))
    }

    override fun getItemCount(): Int = provinsiData.size

    override fun onBindViewHolder(holder: KawalCoronaProvinsiHolder, position: Int) {
        holder.provinsi.text = provinsiData[position].attributes.provinsi
    }

    class KawalCoronaProvinsiHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val provinsi: TextView = itemView.findViewById(R.id.tv_global_country)
        val sembuh: TextView = itemView.findViewById(R.id.tv_global_recovered)
        val positif: TextView = itemView.findViewById(R.id.tv_global_confirmed)
    }
}