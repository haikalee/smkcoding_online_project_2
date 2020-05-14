package com.haikal.project2.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.haikal.project2.Provinsi

import com.haikal.project2.R
import com.haikal.project2.data.Api
import com.haikal.project2.data.lokal.indonesia.IndonesiaDataItem
import com.haikal.project2.util.dismissLoading
import com.haikal.project2.util.showLoading
import kotlinx.android.synthetic.main.fragment_penyebaran_indonesia.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PenyebaranIndonesiaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_penyebaran_indonesia, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(context!!, sw)
        fetchJson()
        btn_provinsi.setOnClickListener {
            val move = Intent(context, Provinsi::class.java)
            startActivity(move)
        }
    }

    private fun fetchJson() {
        val call: Call<List<IndonesiaDataItem>> = Api.services.getDataIndonesia()
        call.enqueue(object: Callback<List<IndonesiaDataItem>>{
            override fun onFailure(call: Call<List<IndonesiaDataItem>>, t: Throwable) {
                dismissLoading(sw)
            }
            override fun onResponse(call: Call<List<IndonesiaDataItem>>, response: Response<List<IndonesiaDataItem>>) {
                dismissLoading(sw)
                tv_indonesia_positif.text = response.body()?.get(0)?.positif.toString()
                tv_indonesia_sembuh.text = response.body()?.get(0)?.sembuh.toString()
            }
        })
    }
}
