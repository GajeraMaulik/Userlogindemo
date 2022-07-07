package com.example.userlogindemo.Retrofit

import com.example.userlogindemo.GetPost.Data
import com.example.userlogindemo.GetPost.PostResponse
import com.example.userlogindemo.Model.Login.Register.Login.Login
import com.example.userlogindemo.Model.Login.Register.Login.LoginRequestBody
import com.example.userlogindemo.Model.Login.Register.RegisterRequestBody
import com.example.userlogindemo.Model.Login.Register.Registration
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface APIServices /*: Call<UploadResponse>*/ {


    // http://192.168.1.3:8000/api/user/register
    @POST("register")
    @Headers(
        "accept:application/json",
        "deviceid:1",
        "devicename:Samsung",
        "osname:PI",
        "osversion:9",
        "platform:android",
        "appversion:0.0",
        "lastsyncdate:21/01/2022",
        "appbuildversion:0.0"
    )
    fun getregister(@Body prams: RegisterRequestBody): Call<Registration>
//    @Headers({
//        "Accept: application/json",
//        "User-Agent: Your-App-Name",
//        "Cache-Control: max-age=640000"
//    })


    //http://192.168.1.3:8000/api/user/login
    //   @FormUrlEncoded
    @POST("login")
    @Headers(
        "accept:application/json",
        "deviceid:1",
        "devicename:Samsung",
        "osname:PI",
        "osversion:9",
        "platform:android",
        "appversion:0.0",
        "lastsyncdate:21/01/2022",
        "appbuildversion:0.0"
    )

    // fun getlogin(@Body prams: UsersLogin):Call<UsersRegisterRepo>
    fun getlogin(@Body prams: LoginRequestBody): Call<Login>

//    -H 'accept: application/json' \
//    -H 'deviceid: 1' \
//    -H 'devicename: Samsung' \
//    -H 'osname: PI' \
//    -H 'osversion: 9' \
//    -H 'platform: android' \
//    -H 'appversion: 0.0' \
//    -H 'lastsyncdate: 21/01/2022' \
//    -H 'appbuildversion: 0.0' \
//    -H 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiYjc2NzVhY2Q3Mjg1MGI5NGMzZjU4MDQ4MzA5M2U1NGIzZjkzMzYwMzZhNzk0M2VlYzI1YzU1ZTZhYzQ0MzViMDQ3OTcwMTYyOWNjZmRkNDEiLCJpYXQiOjE2NTY2NzgwNTIsIm5iZiI6MTY1NjY3ODA1MiwiZXhwIjoxNjg4MjE0MDUyLCJzdWIiOiIyOSIsInNjb3BlcyI6W119.lVYfN64DVmRcZXJNAoY9IjxHd5NkAh9IyD6XHlpuSNadsCSVREad9MUE6ZkFRwqrNc8H7PNYnJcRAdWDmN9JblZ2IaL-UWlB-C7Z0x6QTi8EdCamXZpDvJmkE2HBRsJXndvZEDMeJtxuaDThYXntF1B3jp6MsQpQbikKOJE0kjKbiUBeu_WxyfyeXtxyNJUaqWPY0T58mPJwEXhILVqPbaU6oYNh3U8Z0usSOYvuBHFVFsNH94geec82WIjhwVVhoRKNn0lAExJxAyPSkPKQ4hy4wPqkVCnMGCsCRoHq8tYgTcMoz8a0poduA_2jfRlufKWgACJ0k0BSOJfDsSUf1mWEt2PtsvqOyd7C1LT0srxnpYK3qdl5IjwionvM814NfvUpVaETokOvgWVXOJu-u0oxVLDQND1tmU4Nug_7DPYcXW9vAStRN-d4ezc-9yBbEImTgoZOW_Y4JQtTPYwm-Y1jnKvnfLLl5WkNKRSJngY_AnfdYVF3dGQ4CCYr_23S5PlRWnqUep-WvGrYUnC0AMIr5emEzd-GPIQeK7PXM6UdmnjsZSb78pOiySeJEK5-mE4ozBwOyal_rLrlY_9RyMWXszXaJzOLdRwAR-PPOHKR1C7Ysotr2SI2fDhArlIf2Amfs1y9nVXstcmx-jvaxgxj7HQdSUPJWm2t4lQIiHQ' \
//    -H 'Content-Type: multipart/form-data' \
//    -H 'X-CSRF-TOKEN: ' \

    //http://15.184.130.128/api/user/
    @Multipart
    @POST("add-post")
    @Headers(
        "accept:application/json",
        "deviceid:1",
        "devicename:Samsung",
        "osname:PI",
        "osversion:9",
        "platform:android",
        "appversion:0.0",
        "lastsyncdate:21/01/2022",
        "appbuildversion:0.0",
        "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiYjc2NzVhY2Q3Mjg1MGI5NGMzZjU4MDQ4MzA5M2U1NGIzZjkzMzYwMzZhNzk0M2VlYzI1YzU1ZTZhYzQ0MzViMDQ3OTcwMTYyOWNjZmRkNDEiLCJpYXQiOjE2NTY2NzgwNTIsIm5iZiI6MTY1NjY3ODA1MiwiZXhwIjoxNjg4MjE0MDUyLCJzdWIiOiIyOSIsInNjb3BlcyI6W119.lVYfN64DVmRcZXJNAoY9IjxHd5NkAh9IyD6XHlpuSNadsCSVREad9MUE6ZkFRwqrNc8H7PNYnJcRAdWDmN9JblZ2IaL-UWlB-C7Z0x6QTi8EdCamXZpDvJmkE2HBRsJXndvZEDMeJtxuaDThYXntF1B3jp6MsQpQbikKOJE0kjKbiUBeu_WxyfyeXtxyNJUaqWPY0T58mPJwEXhILVqPbaU6oYNh3U8Z0usSOYvuBHFVFsNH94geec82WIjhwVVhoRKNn0lAExJxAyPSkPKQ4hy4wPqkVCnMGCsCRoHq8tYgTcMoz8a0poduA_2jfRlufKWgACJ0k0BSOJfDsSUf1mWEt2PtsvqOyd7C1LT0srxnpYK3qdl5IjwionvM814NfvUpVaETokOvgWVXOJu-u0oxVLDQND1tmU4Nug_7DPYcXW9vAStRN-d4ezc-9yBbEImTgoZOW_Y4JQtTPYwm-Y1jnKvnfLLl5WkNKRSJngY_AnfdYVF3dGQ4CCYr_23S5PlRWnqUep-WvGrYUnC0AMIr5emEzd-GPIQeK7PXM6UdmnjsZSb78pOiySeJEK5-mE4ozBwOyal_rLrlY_9RyMWXszXaJzOLdRwAR-PPOHKR1C7Ysotr2SI2fDhArlIf2Amfs1y9nVXstcmx-jvaxgxj7HQdSUPJWm2t4lQIiHQ",
       //"Content-Type: multipart/form-data",
       // "X-CSRF-TOKEN:"
    )
    @JvmSuppressWildcards
  //  fun uploadpost(@Part("categories") categories:String,@Part post:MultipartBody.Part,@Part thumbnail:MultipartBody.Part): Call<UploadResponse>
    fun addpostData(@Part("categories") categories: RequestBody, @Part post: MultipartBody.Part, @Part thumbnail: MultipartBody.Part): Call<ResponseBody>
    //
//@PartMap params: Map<String, Any>
//http://15.184.130.128/api/user/get-post?categories=1%2C2&offset=0&limit=10
    
    //{categories=}{offset=}{limit}
    @GET("get-post")
    @Headers(
        "accept:application/json",
        "deviceid:1",
        "devicename:Samsung",
        "osname:PI",
        "osversion:9",
        "platform:android",
        "appversion:0.0",
        "lastsyncdate:21/01/2022",
        "appbuildversion:0.0",
        "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiYjc2NzVhY2Q3Mjg1MGI5NGMzZjU4MDQ4MzA5M2U1NGIzZjkzMzYwMzZhNzk0M2VlYzI1YzU1ZTZhYzQ0MzViMDQ3OTcwMTYyOWNjZmRkNDEiLCJpYXQiOjE2NTY2NzgwNTIsIm5iZiI6MTY1NjY3ODA1MiwiZXhwIjoxNjg4MjE0MDUyLCJzdWIiOiIyOSIsInNjb3BlcyI6W119.lVYfN64DVmRcZXJNAoY9IjxHd5NkAh9IyD6XHlpuSNadsCSVREad9MUE6ZkFRwqrNc8H7PNYnJcRAdWDmN9JblZ2IaL-UWlB-C7Z0x6QTi8EdCamXZpDvJmkE2HBRsJXndvZEDMeJtxuaDThYXntF1B3jp6MsQpQbikKOJE0kjKbiUBeu_WxyfyeXtxyNJUaqWPY0T58mPJwEXhILVqPbaU6oYNh3U8Z0usSOYvuBHFVFsNH94geec82WIjhwVVhoRKNn0lAExJxAyPSkPKQ4hy4wPqkVCnMGCsCRoHq8tYgTcMoz8a0poduA_2jfRlufKWgACJ0k0BSOJfDsSUf1mWEt2PtsvqOyd7C1LT0srxnpYK3qdl5IjwionvM814NfvUpVaETokOvgWVXOJu-u0oxVLDQND1tmU4Nug_7DPYcXW9vAStRN-d4ezc-9yBbEImTgoZOW_Y4JQtTPYwm-Y1jnKvnfLLl5WkNKRSJngY_AnfdYVF3dGQ4CCYr_23S5PlRWnqUep-WvGrYUnC0AMIr5emEzd-GPIQeK7PXM6UdmnjsZSb78pOiySeJEK5-mE4ozBwOyal_rLrlY_9RyMWXszXaJzOLdRwAR-PPOHKR1C7Ysotr2SI2fDhArlIf2Amfs1y9nVXstcmx-jvaxgxj7HQdSUPJWm2t4lQIiHQ",
        //"Content-Type: multipart/form-data",
        // "X-CSRF-TOKEN:"
        )
    fun getAllPost(
                    @Query("categories") categories:String,
                   @Query("offset") offset:String,
                    @Query("limit") limit:String
                ):Call<PostResponse>

    //@QueryMap filter:MutableMap<String,Any>
    //@Query("category") category:String,@Query("offset") offset:Int,@Query("limit") limit:Int
    //@Body prams: PostBody
//

//    @Part("category") category: Int, @PartMap map:Map<String,@JvmSuppressWildcards RequestBody>,@Part("thumbnail") thumbnail :String
}