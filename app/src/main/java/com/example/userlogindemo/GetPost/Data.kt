package com.example.userlogindemo.GetPost

data class Data (
       val allow_commenting: Int,
    val comments: List<Any>,
    val days: String,
    val description: String,
    val hours: String,
    val id: Int,
    val is_blocked: Int,
    val is_reported: Int,
    val is_self_liked: Int,
    val is_updated: Int,
    val masked_thumbnail: String,
    val minutes: String,
     var post: String,
       val post_categories: List<PostCategory>,
    val post_date: String,
    val post_tags: List<Any>,
    val post_time: String,
    val post_timestamp: Int,
    val post_type: String,
    val shareable_link: String,
    val status: String,
    val thumbnail: String,
    val total_dislike: Int,
    val total_likes: Int,
    val total_views: Int,
    val user: User,
    val user_id: Int

 )
 /*constructor()
     constructor(post:String){
         this.post = post
     }

     @JvmName("getPost1")
     fun getPost():String{
         return post
     }

     @JvmName("setPost1")
     fun setPost(post: String){
         this.post = post
     }
 }*/