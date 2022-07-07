package com.example.userlogindemo.GetPost

import android.os.Message
import com.google.gson.annotations.SerializedName

 data class PostResponse(
    var code: Int ,
     var data: List<Data>,
     var message: String,
    var success: Boolean
 )
  /*   constructor()
     constructor(code:Int,data: Data,message: String,sucess:Boolean){
         this.code = code
         this.data = data
         this.message = message
         this.success = success
     }*/

 /*   @JvmName("data")
    fun getData():Data{
        return  data
    }

     @JvmName("setData1")
     fun setData(data: Data){
         this.data = data
     }*/
