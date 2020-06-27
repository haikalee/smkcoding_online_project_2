package com.haikal.project2.fragment

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.ktx.Firebase
import com.haikal.project2.activity.GlobalActivity
import com.haikal.project2.activity.ProvinsiActivity

import com.haikal.project2.R
import com.haikal.project2.activity.LoginActivity
import com.haikal.project2.data.api.Api
import com.haikal.project2.data.mathdro.global.GlobalDetail
import com.haikal.project2.data.kawalcorona.indonesia.IndonesiaItem
import com.haikal.project2.util.dismissLoading
import com.haikal.project2.util.showLoading
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class Dashboard : Fragment() {

    private lateinit var sw: SwipeRefreshLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sw = view.findViewById(R.id.sw)

        showLoading(view.context, sw)
        fetchJsonGlobal()
        fetchJsonIndonesia()

        btn_global_data.setOnClickListener {
            val move = Intent(context, GlobalActivity::class.java)
            startActivity(move)
        }

        btn_provinsi_indonesia.setOnClickListener {
            val move = Intent(context, ProvinsiActivity::class.java)
            startActivity(move)
            getToken()
        }

        btn_covid_wa.setOnClickListener {
            val noWa = "+6281133399000"
            try {
                val pm: PackageManager = view.context.packageManager
                pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
                val wa = Intent(Intent.ACTION_VIEW)
                wa.data = Uri.parse("https://api.whatsapp.com/send?phone=$noWa")
                startActivity(wa)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        btn_logout.setOnClickListener{
            Firebase.auth.signOut()
            onDestroy()
            startActivity(Intent(view.context, LoginActivity::class.java))
        }
    }

    private fun getToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.d("Token", "Gagal")
                }
                val token = task.result?.token
                Log.d("Token", token)
            })
    }

    private fun fetchJsonGlobal() {
        val call: Call<GlobalDetail> = Api.servicesMathdro.getMathdroGlobal()
        call.enqueue(object: Callback<GlobalDetail>{
            override fun onFailure(call: Call<GlobalDetail>, t: Throwable) {
                dismissLoading(sw)
                t.printStackTrace()
            }
            override fun onResponse(call: Call<GlobalDetail>, response: Response<GlobalDetail>) {
                dismissLoading(sw)
                tv_positif.text = response.body()!!.confirmed.value.toString()
                tv_sembuh.text = response.body()!!.recovered.value.toString()
                tv_meninggal.text = response.body()!!.deaths.value.toString()
            }
        })
    }

    private fun fetchJsonIndonesia() {
        val call: Call<List<IndonesiaItem>> = Api.servicesKawalCorona.getKawalCoronaIndonesia()
        call.enqueue(object: Callback<List<IndonesiaItem>> {
            override fun onFailure(call: Call<List<IndonesiaItem>>, t: Throwable) {
                dismissLoading(sw)
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<IndonesiaItem>>, response: Response<List<IndonesiaItem>>) {
                dismissLoading(sw)
                tv_indonesia_positif.text = response.body()!![0].positif
                tv_indonesia_sembuh.text = response.body()!![0].sembuh
                tv_indonesia_meninggal.text = response.body()!![0].meninggal
            }
        })
    }
}