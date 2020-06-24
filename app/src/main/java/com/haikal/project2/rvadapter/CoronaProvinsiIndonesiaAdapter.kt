package com.haikal.project2.rvadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haikal.project2.R
import com.haikal.project2.data.kawalcorona.Attributes
import com.haikal.project2.data.kawalcorona.ProvinsiItem

class CoronaProvinsiIndonesiaAdapter(val context: Context,val data: List<ProvinsiItem>): RecyclerView.Adapter<CoronaProvinsiIndonesiaAdapter.CoronaProvinsiIndonesiaHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoronaProvinsiIndonesiaHolder {
        return CoronaProvinsiIndonesiaHolder(LayoutInflater.from(context).inflate(R.layout.data_item, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CoronaProvinsiIndonesiaHolder, position: Int) {
        holder.provinsi.text = data[position].attributes.provinsi
        holder.positif.text = data[position].attributes.kasusPosi.toString()
        holder.meninggal.text = data[position].attributes.kasusMeni.toString()
        holder.sembuh.text = data[position].attributes.kasusSemb.toString()
    }

    class CoronaProvinsiIndonesiaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val provinsi: TextView = itemView.findViewById(R.id.tv_name)
        val positif: TextView = itemView.findViewById(R.id.tv_confirmed)
        val meninggal: TextView = itemView.findViewById(R.id.tv_death)
        val sembuh: TextView = itemView.findViewById(R.id.tv_recovered)
    }
}