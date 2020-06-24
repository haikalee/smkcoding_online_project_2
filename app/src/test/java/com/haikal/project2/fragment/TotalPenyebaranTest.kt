package com.haikal.project2.fragment//package com.haikal.project2.fragment
//
//import com.haikal.project2.data.api.Api
//import org.junit.Test
//
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class TotalPenyebaranTest {
//
//    @Test
//    fun onCreateView() {
//    }
//
//    @Test
//    fun onViewCreated() {
//        val call: Call<List<GlobalPositive>> = Api.servicesKawalCorona.getKawalCoronaGlobal()
//        call.enqueue(object: Callback<List<GlobalPositive>> {
//            override fun onFailure(call: Call<List<GlobalPositive>>, t: Throwable) {
////                dismissLoading(sw)
//            }
//            override fun onResponse(call: Call<List<GlobalPositive>>, response: Response<List<GlobalPositive>>) {
////                dismissLoading(sw)
////                print(response.body()!!.get(0).value)
//                val result = response.body()?.get(0)?.value
//            }
//        })
//    }
//}