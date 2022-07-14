package com.example.userlogindemo.Retrofit

import android.text.Editable
import com.example.userlogindemo.DeletePost.PostCategory
import com.example.userlogindemo.DeletePost.PostDeleteResponse
import com.example.userlogindemo.GetPost.Data
import com.example.userlogindemo.GetPost.PostResponse
import com.example.userlogindemo.GetUser.UsersResponse
import com.example.userlogindemo.Model.Login.Register.Login.Login
import com.example.userlogindemo.Model.Login.Register.Login.LoginRequestBody
import com.example.userlogindemo.Model.Login.Register.RegisterRequestBody
import com.example.userlogindemo.Model.Login.Register.Registration
import com.example.userlogindemo.Report.ReportResponse
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
//    @Part("category") category: Int, @PartMap map:Map<String,@JvmSuppressWildcards RequestBody>,@Part("thumbnail") thumbnail :String

    @GET("notifications")
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
        "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiODVjZTUxZTc4MGYzNmQwZDM5Mjk1MTZkYmQ5ZGUxOTI2OTkwNzliMjA5NmYzOTk1ZWQ1Y2E3YzUzMzdiYzQ1YmY4ZWExZmY1ODFhODFkZjAiLCJpYXQiOjE2NTcyNjI5MzQsIm5iZiI6MTY1NzI2MjkzNCwiZXhwIjoxNjg4Nzk4OTM0LCJzdWIiOiIyOSIsInNjb3BlcyI6W119.hI0Se1qNWDQHZlWW-3BO-iArZsz8zdYkykzchIHTjG_UwesbfHALiER0plTy4VFO4SpU0D5piAOYfuu0mELrQfwzGCpuxLvFMGd5HVgj49Cqnm7wSKUJOhcA-XPiit-kWR_nKFPQM2h51aavb4np_8aRt2uD7NLj0c3EceIkrjOiWyqyGkJ3KwIjRBFo76altPZLrehndSDEfXNl4ASgcWpnn0r-HTldN7OE98nvMKIYW4BnuVP4wh_l8bKWX0lAsBn284luMh_ChQ81SEuQ_1MOdk-9f1fpFE2XutR_hO-ixGNr6LQrubkhFzY8_d7gTHFCm0KmdVITjuihgqx7bmfxMdcEnGEMXT06ij2JhlzxOyJE0XgwpJVG8a9vWDWgWMxefAHUACnayIjvP0sBquR50odsdUJBpcdUYxJZ77pwTLAul-DJN9v6SToyvzWHVn9zt0pCvETt1nVMSvFzN4lMl-TkjqDINU6Wr6rYuIAqy8esRK3l-pJphgRqsPX4IAyxF2Gfgmct7Ph-S8nKIEL8fVxRE6qGKvFiqXGY2XC1EjwOIXIl35DjJKfqKZZTJ8wrCCkeiMg-tdmNvfIrY6zfKg24pnxhTPsNNu0ywR9V8tzCd7hphJ-8K0fp7skOkKTA4SQAh7zr1xuxnidX4b_QvJe56T9QaS-Ab1zdBEg",
        "X-CSRF-TOKEN:"
    )
    fun getNotification():Call<ResponseBody>

    @GET("user")
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
        "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiODVjZTUxZTc4MGYzNmQwZDM5Mjk1MTZkYmQ5ZGUxOTI2OTkwNzliMjA5NmYzOTk1ZWQ1Y2E3YzUzMzdiYzQ1YmY4ZWExZmY1ODFhODFkZjAiLCJpYXQiOjE2NTcyNjI5MzQsIm5iZiI6MTY1NzI2MjkzNCwiZXhwIjoxNjg4Nzk4OTM0LCJzdWIiOiIyOSIsInNjb3BlcyI6W119.hI0Se1qNWDQHZlWW-3BO-iArZsz8zdYkykzchIHTjG_UwesbfHALiER0plTy4VFO4SpU0D5piAOYfuu0mELrQfwzGCpuxLvFMGd5HVgj49Cqnm7wSKUJOhcA-XPiit-kWR_nKFPQM2h51aavb4np_8aRt2uD7NLj0c3EceIkrjOiWyqyGkJ3KwIjRBFo76altPZLrehndSDEfXNl4ASgcWpnn0r-HTldN7OE98nvMKIYW4BnuVP4wh_l8bKWX0lAsBn284luMh_ChQ81SEuQ_1MOdk-9f1fpFE2XutR_hO-ixGNr6LQrubkhFzY8_d7gTHFCm0KmdVITjuihgqx7bmfxMdcEnGEMXT06ij2JhlzxOyJE0XgwpJVG8a9vWDWgWMxefAHUACnayIjvP0sBquR50odsdUJBpcdUYxJZ77pwTLAul-DJN9v6SToyvzWHVn9zt0pCvETt1nVMSvFzN4lMl-TkjqDINU6Wr6rYuIAqy8esRK3l-pJphgRqsPX4IAyxF2Gfgmct7Ph-S8nKIEL8fVxRE6qGKvFiqXGY2XC1EjwOIXIl35DjJKfqKZZTJ8wrCCkeiMg-tdmNvfIrY6zfKg24pnxhTPsNNu0ywR9V8tzCd7hphJ-8K0fp7skOkKTA4SQAh7zr1xuxnidX4b_QvJe56T9QaS-Ab1zdBEg",
    )
    fun getUsers(@Query("id") id: Editable):Call<UsersResponse>

    @Multipart
    @POST("post-delete")
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
        "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiODVjZTUxZTc4MGYzNmQwZDM5Mjk1MTZkYmQ5ZGUxOTI2OTkwNzliMjA5NmYzOTk1ZWQ1Y2E3YzUzMzdiYzQ1YmY4ZWExZmY1ODFhODFkZjAiLCJpYXQiOjE2NTcyNjI5MzQsIm5iZiI6MTY1NzI2MjkzNCwiZXhwIjoxNjg4Nzk4OTM0LCJzdWIiOiIyOSIsInNjb3BlcyI6W119.hI0Se1qNWDQHZlWW-3BO-iArZsz8zdYkykzchIHTjG_UwesbfHALiER0plTy4VFO4SpU0D5piAOYfuu0mELrQfwzGCpuxLvFMGd5HVgj49Cqnm7wSKUJOhcA-XPiit-kWR_nKFPQM2h51aavb4np_8aRt2uD7NLj0c3EceIkrjOiWyqyGkJ3KwIjRBFo76altPZLrehndSDEfXNl4ASgcWpnn0r-HTldN7OE98nvMKIYW4BnuVP4wh_l8bKWX0lAsBn284luMh_ChQ81SEuQ_1MOdk-9f1fpFE2XutR_hO-ixGNr6LQrubkhFzY8_d7gTHFCm0KmdVITjuihgqx7bmfxMdcEnGEMXT06ij2JhlzxOyJE0XgwpJVG8a9vWDWgWMxefAHUACnayIjvP0sBquR50odsdUJBpcdUYxJZ77pwTLAul-DJN9v6SToyvzWHVn9zt0pCvETt1nVMSvFzN4lMl-TkjqDINU6Wr6rYuIAqy8esRK3l-pJphgRqsPX4IAyxF2Gfgmct7Ph-S8nKIEL8fVxRE6qGKvFiqXGY2XC1EjwOIXIl35DjJKfqKZZTJ8wrCCkeiMg-tdmNvfIrY6zfKg24pnxhTPsNNu0ywR9V8tzCd7hphJ-8K0fp7skOkKTA4SQAh7zr1xuxnidX4b_QvJe56T9QaS-Ab1zdBEg",
    )
    fun postDelele(@Part post_id:MultipartBody.Part):Call<PostDeleteResponse>


    @GET("report-tag")
    @Headers(
        "accept:application/json"
    )
    fun getReporttag():Call<ReportResponse>

}