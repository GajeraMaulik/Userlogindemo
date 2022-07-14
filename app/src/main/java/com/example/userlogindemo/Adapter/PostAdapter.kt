package com.example.userlogindemo.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.userlogindemo.Activity.ReportActivity
import com.example.userlogindemo.DeletePost.PostCategory
import com.example.userlogindemo.DeletePost.PostDeleteResponse
import com.example.userlogindemo.GetPost.Data
import com.example.userlogindemo.GetUser.UsersActivity
import com.example.userlogindemo.R
import com.example.userlogindemo.Retrofit.Client
import com.example.userlogindemo.databinding.ViewPostBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.view_post.view.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PostAdapter: RecyclerView.Adapter<PostAdapter.PostViewHolder>{
    lateinit var context: Context
    lateinit var postList:ArrayList<Data>


    var postCategory :PostCategory?=null
    constructor()
    constructor(context: Context, postList: ArrayList<Data>){
        this.context=context
        this.postList=postList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val  binding=ViewPostBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(binding)
    }
    override fun onBindViewHolder(holder: PostViewHolder, @SuppressLint("RecyclerView") position: Int) {
        with(holder){
            with(postList[position]){
                Glide.with(context).load(post)
                    .placeholder(R.drawable.loading)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(itemView.postImage)
             //   binding.txtGallery.text = user.first_name
                
                binding.postDeleteView.setOnClickListener(View.OnClickListener {
                    val builder = AlertDialog.Builder(context)
                    val item: Data = postList[position]
                    //set title for alert dialog
                    builder.setTitle(R.string.dialogTitle)
                    //set message for alert dialog
                    builder.setMessage(R.string.dialogMessage)
                    builder.setIcon(android.R.drawable.ic_dialog_alert)

                    //performing positive action
                    builder.setPositiveButton("Yes") { dialogInterface, which ->
                        Log.d("postid", " --post_id -->${postCategory?.post_id}")



                        val postPart: MultipartBody.Part = MultipartBody.Part.createFormData("post_id","$id")

                        val getResponse = Client.addPost()
                        Log.d("postdetele", " --getresponse -->$getResponse")
                        val call = getResponse.postDelele(postPart)

                        call.enqueue(object :Callback<PostDeleteResponse>{
                            override fun onResponse(call: Call<PostDeleteResponse>, response: Response<PostDeleteResponse>) {



                                Log.d("postdetele", " --code-->${response.code()}")
                                Log.d("postdetele", " --message-->${response.message()}")
                                Log.d("postdetele", " --body-->${response.body()}")
                                Log.d("postdetele", " --headers-->${response.headers()}")

                                if (response.body()?.success!!){


                                    val postLists = response.body()!!.data.post_categories
                                    Log.d("postdetele", " --isSuccess-->${response.isSuccessful}")


                                    Log.d("postdetele", " -- after code-->${response.code()}")
                                    Log.d("postdetele", " --after message-->${response.message()}")
                                    Log.d("postdetele", " --after body-->${response.body()}")
                                    Log.d("postdetele", " --after headers-->${response.headers()}")
                                    val postclassd: PostDeleteResponse = response.body()!!
                                    Log.d("postdelete", " --arraysize -->${postList.size}")

                                    /*                      for (i in postList!!.indices)
                                    {
                                     //   Log.d("mydatapost", " --postLists -->${dataclass.get(i).id}")
                                        var lastItem = postList!!.zipWithNext()
                                            .any { it.first.equals("To") && !it.second.equals("To") }
                                        if (i < postList!!.lastIndex) {
                                            if(postList!![i].equals("To") && !postLists[i+1].equals("To")) {
                                                lastItem = true
                                            }
                                        }

                                      // notifyDataSetChanged()



                                    }*/


                                    postList.removeAt(position)

                                    //   notifyDataSetChanged()
                                      PostAdapter(context,postList)
                                    //notifyItemRemoved(position)
                                  //  notifyItemRangeChanged(position, postList!!.size)





                                    Toast.makeText(context, "Post Deleted ${response.body()?.data!!.id}", Toast.LENGTH_SHORT).show()
                                    Log.d("postdetele", " --postId-->${response.body()?.data!!.id}")

                                }
                            }

                            override fun onFailure(call: Call<PostDeleteResponse>, t: Throwable) {
                                Log.d("postdetele", t.toString() + "")
                                Log.d("postdetele", " --error  -->$t")
                                Toast.makeText(context, "Api Fail   $t", Toast.LENGTH_SHORT).show()
                            }

                        })


                        Toast.makeText(context, "Removed $postList", Toast.LENGTH_LONG).show()
                    }

                    //performing negative action
                    builder.setNegativeButton("No")
                    { dialogInterface, which ->
                        Log.d("postid", " --post_id -->${postCategory?.post_id}")
                        Log.d("postdelete", " --arraysize -->${postList.count()}")
                        Log.d("postdelete", " --arraypost-->${postList}")
                        builder.setOnDismissListener(dialogInterface.dismiss())

                        //  Toast.makeText(context, "clicked No", Toast.LENGTH_LONG).show()
                    }
                    builder.show()
                })

                binding.postReport.setOnClickListener {

                    context.startActivity(Intent(context,ReportActivity::class.java))

                 // openBottomSheetdialog(it)
                }
                    
            }
            
        }
        
    }
    fun openBottomSheetdialog(view: View){
        val dialog = BottomSheetDialog(context)
       val  view = LayoutInflater.from(context).inflate(R.layout.activity_report,view.findViewById<ConstraintLayout>(R.id.bottomsheet))
        view.findViewById<View>(R.id.rButton).setOnClickListener {
            Toast.makeText(context, "Oder Done", Toast.LENGTH_SHORT).show()
        }
      /*  view.findViewById<ImageView>(R.id.close).setOnClickListener {
            dialog.dismiss()
        }*/
        dialog.window!!.setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        dialog.window!!.setGravity (Gravity.BOTTOM);
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(view)
        dialog.show()
    }

     override fun getItemCount(): Int {
         return postList.size
     }
    class PostViewHolder(val binding: ViewPostBinding):RecyclerView.ViewHolder(binding.root) {

    }




}



private fun AlertDialog.Builder.setOnDismissListener(dismiss: Unit) {

}
