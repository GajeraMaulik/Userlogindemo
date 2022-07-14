package com.example.userlogindemo.Activity

import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.userlogindemo.Adapter.ReportAdapter
import com.example.userlogindemo.R
import com.example.userlogindemo.Report.Data
import com.example.userlogindemo.Report.ReportResponse
import com.example.userlogindemo.Retrofit.APIServices
import com.example.userlogindemo.Retrofit.Client
import com.example.userlogindemo.databinding.ActivityReportBinding
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_report.*
import kotlinx.android.synthetic.main.item_report.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ReportActivity : AppCompatActivity() {

    lateinit var binding : ActivityReportBinding
    lateinit var adapter:ReportAdapter
     var data:Data? = null

     var reportList : ArrayList<Data> = ArrayList()
    var ll: LinearLayout? = null
    lateinit var radibtn:RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val actionBar = supportActionBar
        actionBar!!.title = "Report"
        actionBar.setDisplayHomeAsUpEnabled(true)


       radibtn = RadioButton(this@ReportActivity)



        getReportTag()
    }


    fun getReportTag(){
        val apiService: APIServices = Client.getUsers()
        val call: Call<ReportResponse> = apiService.getReporttag()
        call.enqueue(object : Callback<ReportResponse> {
            override fun onResponse(call: Call<ReportResponse>, response: Response<ReportResponse>
            ) {
                Log.d("report", " --code-->${response.code()}")
                Log.d("report", " --message-->${response.message()}")
                Log.d("report", " --body-->${response.body()}")
                Log.d("report", " --headers-->${response.headers()}")

                if (response.body()?.success!!) {
                    Log.d("report", " -- after code-->${response.code()}")
                    Log.d("report", " --after message-->${response.message()}")
                    Log.d("report", " --after body-->${response.body()}")
                    Log.d("report", " --after headers-->${response.headers()}")

                    val reportclass: ReportResponse = response.body()!!
                    val dataclass: ArrayList<Data> = reportclass.data as ArrayList<Data>;
                    for (i in 0..dataclass.size-1)
                    {
                        Log.d("mydatapost", " --postLists name  -->${dataclass.get(i).name}")
                        val chip = Chip(this@ReportActivity)
                        chip.text= dataclass.get(i).name
                        chip.id = dataclass.get(i).id

                        // necessary to get single selection working
                        chip.isCheckable = true
                        //    chip.isClickable = true
                        chip.isCloseIconVisible = false

                        chipGroup.addView(chip)

                    }
                    reportList.addAll(dataclass)

                }

            }

            override fun onFailure(call: Call<ReportResponse>, t: Throwable) {
                Log.d("report", t.toString() + "")
                Log.d("report", " --error  -->$t")
                Toast.makeText(this@ReportActivity, "Api Fail   $t", Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}


