package com.haikal.project2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.haikal.project2.R
import com.haikal.project2.data.api.Api
import com.haikal.project2.data.kawalcorona.provinsi.ProvinsiItem
import com.haikal.project2.rvadapter.CoronaProvinsiIndonesiaAdapter
import com.haikal.project2.util.dismissLoading
import com.haikal.project2.util.showLoading
import kotlinx.android.synthetic.main.activity_provinsi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProvinsiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provinsi)
        showLoading(this, sw_provinsi)
        fetchJson()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun fetchJson() {
        val call: Call<List<ProvinsiItem>> = Api.servicesKawalCorona.getKawalCoronaProvinsi()
        call.enqueue(object: Callback<List<ProvinsiItem>> {
            override fun onFailure(call: Call<List<ProvinsiItem>>, t: Throwable) {
                dismissLoading(sw_provinsi)
                print(t.printStackTrace())
            }
            override fun onResponse(call: Call<List<ProvinsiItem>>, response: Response<List<ProvinsiItem>>) {
                dismissLoading(sw_provinsi)
                showData(response.body()!!)
            }
        })
    }

    private fun showData(dataProvinsi: List<ProvinsiItem>) {
        rv_provinsi.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CoronaProvinsiIndonesiaAdapter(context, dataProvinsi)
        }
    }
}
