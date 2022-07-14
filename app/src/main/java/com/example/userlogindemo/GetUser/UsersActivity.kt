package com.example.userlogindemo.GetUser

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import com.example.userlogindemo.GetPost.Data
import com.example.userlogindemo.GetPost.PostResponse
import com.example.userlogindemo.R
import com.example.userlogindemo.Retrofit.Client
import kotlinx.android.synthetic.main.activity_users.*
import kotlinx.android.synthetic.main.view_users.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

class UsersActivity : AppCompatActivity() {

    var  categorynames :ArrayList<String> = ArrayList()
    private lateinit var pDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        
        initDialog()
    //    getUsers()

        fechData.setOnClickListener {
            var input = etUserId.text
            fetchData(input)
        }
    }

    fun fetchData(input: Editable){
        showpDialog()

        val getResponse = Client.getUsers()
        Log.d("getusers", " --getresponse -->$getResponse")
        val call = getResponse.getUsers(input)
        Log.d("getusers", " --call -->$call")
        call.enqueue(object : Callback<UsersResponse> {
            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {
                hidepDialog()

                Log.d("getusers", " --code-->${response.code()}")
                Log.d("getusers", " --message-->${response.message()}")
                Log.d("getusers", " --body-->${response.body()}")
                Log.d("getusers", " --headers-->${response.headers()}")

                val postclassd: UsersResponse = response.body()!!
                val dataclass: ArrayList<UserCategory> = postclassd.data.user_category as ArrayList<UserCategory>;
       //         val categorydata :ArrayList<UserCategory> = postclassd.data as


                if(response.body()?.success!!){

                    for (i in 0..dataclass.size-1){

                        var usercat =response.body()?.data?.user_category!!.get(i)


                        Log.d("getusers", " --usercat-->${usercat}")
                        firstName.text =  response.body()?.data!!.first_name
                        lastName.text = response.body()?.data!!.last_name
                        userName.text = response.body()?.data!!.username
                        emailId.text = response.body()?.data!!.email
                        loginType.text = response.body()?.data!!.login_type
                        userStatus.text = response.body()?.data!!.status


                    //    for (i in 0..usercat.name){

                            Log.d("getusers", " --iiiiiiii-->$i")
                        //    userCategory.text =

                   //     }
                        val userCategoryName = usercat.name
                        categorynames.addAll(listOf(userCategoryName))
                        Log.d("getusers", " --categorynames-->${categorynames}")


                        userCategory.text = categorynames.toString()





                    }


                }else {
                    val incorrectValue = "Incorrect detail"
                    firstName.text = incorrectValue
                    lastName.text = incorrectValue
                    userName.text = incorrectValue
                    emailId.text = incorrectValue
                    loginType.text = incorrectValue
                    userStatus.text = incorrectValue
                    userCategory.text = incorrectValue

                    //  plot.text = response.getString("Plot")
                    //name.text = response.getString("Title")+"\n\n"+"Writer: "+response.getString("Writer")
                }

            }

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                hidepDialog()
                Log.d("fsill", t.toString() + "")
                Log.d("getusers", " --error  -->$t")
                Toast.makeText(this@UsersActivity, "Api Fail   $t", Toast.LENGTH_SHORT).show()
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