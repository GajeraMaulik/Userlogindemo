package com.example.userlogindemo.GetNotifications

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.userlogindemo.Adapter.PostAdapter
import com.example.userlogindemo.GetPost.Data
import com.example.userlogindemo.GetPost.PostResponse
import com.example.userlogindemo.R
import com.example.userlogindemo.Retrofit.APIServices
import com.example.userlogindemo.Retrofit.Client
import kotlinx.android.synthetic.main.activity_post.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationActivity : AppCompatActivity() {
    private lateinit var pDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        initDialog()
        getNotification()
    }


    fun getNotification() {

        showpDialog()


        //val postResponse = PostBody("1%2C2",0,10)

        val getResponse = Client.addPost()
        Log.d("allpost", " --getresponse -->$getResponse")
        val call = getResponse.getNotification()
        call.enqueue(object :Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("allnotification", " --code-->${response.code()}")
                Log.d("allnotification", " --message-->${response.message()}")
                Log.d("allnotification", " --body-->${response.body()}")
                Log.d("allnotification", " --headers-->${response.headers()}")

                if (response.isSuccessful){
                    Log.d("allpost", " --isSuccess-->${response.isSuccessful}")

                    Log.d("allpost", " --isSuccess-->${response.body()}")
                }else {
                    hidepDialog()
                    Toast.makeText(this@NotificationActivity, "${response.body()}", Toast.LENGTH_SHORT).show()
                    Log.d("allpost", " --false message -->${response.message()}")
                    Log.d("allpost", " --false code-->${response.code()}")
                    Log.d("allpost", " --false data-->${response.body()}")

                }

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                hidepDialog()
                Log.d("fsill", t.toString() + "")
                Log.d("allpost", " --error  -->$t")
                Toast.makeText(this@NotificationActivity, "Api Fail   $t", Toast.LENGTH_SHORT).show()
            }

        })


    }


    protected fun initDialog() {

        pDialog = ProgressDialog(this)
        pDialog.setMessage(getString(R.string.msg_loading))
        pDialog.setCancelable(true)
    }


    protected fun showpDialog() {

        if (!pDialog.isShowing) pDialog.show()
    }

    protected fun hidepDialog() {

        if (pDialog.isShowing) pDialog.dismiss()
    }

}