package com.haikal.project2.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.haikal.project2.Api
import com.haikal.project2.GlobalDataItem
import com.haikal.project2.KawalCoronaGlobalAdapter
import com.haikal.project2.R
import kotlinx.android.synthetic.main.fragment_penyebaran.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class PenyebaranFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_penyebaran, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val call: Call<List<GlobalDataItem>> = Api.services.getData()
        call.enqueue(object: Callback<List<GlobalDataItem>>{
            override fun onFailure(call: Call<List<GlobalDataItem>>, t: Throwable) {
                print(t.printStackTrace())
            }

            override fun onResponse(call: Call<List<GlobalDataItem>>, response: Response<List<GlobalDataItem>>) {
                showData(response.body()!!)
            }
        })
    }
    private fun showData(corona: List<GlobalDataItem>) {
        rv_global.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = KawalCoronaGlobalAdapter(corona)
        }
    }
}
