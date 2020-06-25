package com.haikal.project2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.haikal.project2.R
import com.haikal.project2.data.api.Api
import com.haikal.project2.data.mathdro.global.GlobalDataItem
import com.haikal.project2.rvadapter.CoronaGlobalAdapter
import com.haikal.project2.util.dismissLoading
import com.haikal.project2.util.showLoading
import kotlinx.android.synthetic.main.fragment_penyebaran_global.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GlobalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global)
        showLoading(this, sw_global)
        fetchJson()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun fetchJson() {
        val call: Call<List<GlobalDataItem>> = Api.servicesMathdro.getMathdroDataGlobal()
        call.enqueue(object: Callback<List<GlobalDataItem>> {
            override fun onFailure(call: Call<List<GlobalDataItem>>, t: Throwable) {
                dismissLoading(sw_global)
                print(t.printStackTrace())
            }
            override fun onResponse(call: Call<List<GlobalDataItem>>, response: Response<List<GlobalDataItem>>) {
                dismissLoading(sw_global)
                showData(response.body()!!)
            }
        })
    }

    private fun showData(corona: List<GlobalDataItem>) {
        rv_global.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CoronaGlobalAdapter(context, corona)
        }
    }
}
