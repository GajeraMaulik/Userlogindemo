package com.example.userlogindemo.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.userlogindemo.GetPost.Data
import com.example.userlogindemo.GetPost.PostResponse
import com.example.userlogindemo.R
import com.example.userlogindemo.databinding.ViewPostBinding
import com.example.userlogindemo.postPath
import kotlinx.android.synthetic.main.view_post.view.*

class PostAdapter: RecyclerView.Adapter<PostAdapter.PostViewHolder>{
    lateinit var context: Context
    lateinit var postList:ArrayList<Data>
    lateinit  var data : Data
    constructor()
    constructor(context: Context, postList: ArrayList<Data>){
        this.context=context
        this.postList=postList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val  binding=ViewPostBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(binding,)
    }
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        with(holder){
            with(postList[position]){
                Glide.with(context).load(post)
                    .placeholder(R.drawable.loading)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(itemView.postImage)

            }
        }
    }

     override fun getItemCount(): Int {
         return postList.size
     }
    class PostViewHolder(val binding: ViewPostBinding):RecyclerView.ViewHolder(binding.root) {

    }



}
