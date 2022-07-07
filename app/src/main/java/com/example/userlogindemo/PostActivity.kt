package com.example.userlogindemo

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.widget.Toast
import com.example.userlogindemo.Adapter.PostAdapter
import com.example.userlogindemo.GetPost.Data
import com.example.userlogindemo.GetPost.PostResponse
import com.example.userlogindemo.Retrofit.Client
import kotlinx.android.synthetic.main.activity_post.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostActivity : AppCompatActivity() {

    private lateinit var pDialog: ProgressDialog
    var postList: ArrayList<Data> = ArrayList()
    lateinit var postAdapter: PostAdapter
    lateinit var postResponse: PostResponse
    lateinit var data:Data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)


       // postAdapter = PostAdapter(this,postList)


        initDialog()
        getPosts()

    }

    fun getPosts() {

        showpDialog()

        val map: MutableMap<String, Any> = HashMap()
        map.put("categories", "1%2C2")
        map.put("offset", 0)
        map.put("limit", 10)

        d("allpost", "--map --> $map")

        //val postResponse = PostBody("1%2C2",0,10)

        val getResponse = Client.addPost()
        d("allpost", " --getresponse -->$getResponse")
        val call = getResponse.getAllPost("1,2,3,4,5,6,7,8,9,10,11,12,13,14,19,20", "0", "10")
        d("allpost", " --call -->$call")
        call.enqueue(object : Callback<PostResponse> {
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                d("allpost", " --code-->${response.code()}")
                d("allpost", " --message-->${response.message()}")
                d("allpost", " --body-->${response.body()}")
                d("allpost", " --headers-->${response.headers()}")

             //   postList.clear()
            //    try {

                    if (response.body()?.success!!) {
                        d("allpost", " --isSuccess-->${response.isSuccessful}")

                        d("allpost", " --isSuccess-->${response.body()}")

                        val postclassd: PostResponse= response.body()!!
                            val dataclass: ArrayList<Data> = postclassd.data as ArrayList<Data>;

                              d("mydatapost","data  -->${dataclass}")

                                for (i in 0..dataclass.size-1)
                                {
                                    d("mydatapost", " --postLists -->${dataclass.get(i).post}")

                                }
                                 postList.addAll(dataclass)



                                 postAdapter = PostAdapter(this@PostActivity, postList)
                                 rvPostList.adapter = postAdapter




                        d("allpost", " --postLists -->$dataclass")



                     //    d("allpost", " --postLists -->$postLists")

                         //   val post =response.body()?.setData()



                        d("allpost", " --afeter add -->$postList")


                        Toast.makeText(this@PostActivity, """success ${response.code()}""", Toast.LENGTH_SHORT).show()
                        Toast.makeText(this@PostActivity, """success body  ${response.body().toString()}""", Toast.LENGTH_SHORT).show()
                        d("allpost", " --true body -->${response.body()?.data.toString()}")
                        d("allpost", " --true message -->${response.message()}")
                        d("allpost", " --true code-->${response.code()}")
                        d("allpost", " --true data-->${response.body()}")
                        hidepDialog()


                    } else {
                        hidepDialog()
                        Toast.makeText(this@PostActivity, "${response.body()}", Toast.LENGTH_SHORT).show()
                        d("allpost", " --false message -->${response.message()}")
                        d("allpost", " --false code-->${response.code()}")
                        d("allpost", " --false data-->${response.body()}")

                    }

               /* }catch (e:Exception){
                    e.printStackTrace()
                }*/
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                hidepDialog()
                Log.d("fsill", t.toString() + "")
                d("allpost", " --error  -->$t")
                Toast.makeText(this@PostActivity, "Api Fail   $t", Toast.LENGTH_SHORT).show()
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

private fun <E> ArrayList<E>.addAll(elements: Array<String>) {

}
