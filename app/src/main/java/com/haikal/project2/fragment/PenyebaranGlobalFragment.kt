package com.haikal.project2.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.haikal.project2.data.Api
import com.haikal.project2.rvadapter.CoronaGlobalAdapter
import com.haikal.project2.R
import com.haikal.project2.data.mathdro.global.GlobalDataItem
import com.haikal.project2.util.dismissLoading
import com.haikal.project2.util.showLoading
import kotlinx.android.synthetic.main.fragment_penyebaran_global.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class PenyebaranGlobalFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_penyebaran_global, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(context!!, sw_global)
        fetchJson()
    }
    private fun fetchJson() {
        val call: Call<List<GlobalDataItem>> = Api.servicesMathdro.getMathdroDataGlobal()
        call.enqueue(object: Callback<List<GlobalDataItem>>{
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
            adapter = CoronaGlobalAdapter(
                context,
                corona
            )
        }
    }
}
