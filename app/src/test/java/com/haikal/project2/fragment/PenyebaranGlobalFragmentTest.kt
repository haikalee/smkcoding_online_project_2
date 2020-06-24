package com.haikal.project2.fragment

import com.haikal.project2.data.Api
import com.haikal.project2.data.mathdro.global.GlobalDataItem
import org.junit.Test

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PenyebaranGlobalFragmentTest {

    @Test
    fun onCreateView() {
    }

    @Test
    fun onViewCreated() {

        val call: Call<List<GlobalDataItem>> = Api.servicesMathdro.getMathdroDataGlobal()
        call.enqueue(object: Callback<List<GlobalDataItem>> {
            override fun onFailure(call: Call<List<GlobalDataItem>>, t: Throwable) {
//                dismissLoading(sw_global)
                print(t.printStackTrace())
            }
            override fun onResponse(call: Call<List<GlobalDataItem>>, response: Response<List<GlobalDataItem>>) {
//                dismissLoading(sw_global)
                val r = response.body()
//                print(response.body())
            }
        })
    }
}