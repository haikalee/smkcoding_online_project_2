package com.haikal.project2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.haikal.project2.data.Api
import com.haikal.project2.data.lokal.provinsi.ProvinsiDataItem
import com.haikal.project2.rvadapter.KawalCoronaProvinsiAdapter
import com.haikal.project2.util.dismissLoading
import com.haikal.project2.util.showLoading
import kotlinx.android.synthetic.main.activity_provinsi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Provinsi : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provinsi)
        showLoading(this, sw_provinsi)
        fetchJson()
    }
    fun fetchJson() {
        val call: Call<List<ProvinsiDataItem>> = Api.services.getDataProvinsi()
        call.enqueue(object: Callback<List<ProvinsiDataItem>>{
            override fun onFailure(call: Call<List<ProvinsiDataItem>>, t: Throwable) {
                dismissLoading(sw_provinsi)
            }

            override fun onResponse(call: Call<List<ProvinsiDataItem>>, response: Response<List<ProvinsiDataItem>>) {
                dismissLoading(sw_provinsi)
                showData(response.body()!!)
            }

        })
    }
    fun showData(corona: List<ProvinsiDataItem>) {
        rv_provinsi.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = KawalCoronaProvinsiAdapter(corona)
        }
    }
}
