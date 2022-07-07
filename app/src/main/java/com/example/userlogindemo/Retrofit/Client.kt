package com.example.userlogindemo.Retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object Client {

    private lateinit var okHttpClient: OkHttpClient
//    lateinit var apiService: APIServices

//    lateinit var api:APIServices
//    private var retrofit: Retrofit? = null

    var serviceCall:APIServices ?=null
    var gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .create()
    fun  getData() : Retrofit {
        var  interceptor  : HttpLoggingInterceptor  = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        var client:  OkHttpClient =  OkHttpClient.Builder()
         //   .addInterceptor(interceptor)

            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(15, TimeUnit.SECONDS)

            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
     /*   serviceCall =  Retrofit.Builder()//"http://15.184.130.128/api/user/
            .baseUrl("http://15.184.130.128/api/user/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(APIServices::class.java)*/


        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("http://15.184.130.128/api/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val apiServices: APIServices by lazy {
            retrofit.create(APIServices::class.java)
        }
        return retrofit

      //  return serviceCall!!
    }

  fun getRetroInstance(): Retrofit {

        val YOURKEY = "0a9c666ed1msh5ab2e3f50223725p117176jsn0b13bc7107e4"

        val logging = HttpLoggingInterceptor()
        logging.level = (HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
        client.addInterceptor(logging)


        okHttpClient = OkHttpClient.Builder()
             .addInterceptor(logging)
            // .addInterceptor(interceptor)
            .connectTimeout(2, TimeUnit.MINUTES)
            .callTimeout(15, TimeUnit.SECONDS)
           // .connectTimeout(310, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
           /*  .addInterceptor { chain->
                  val request =chain.request().newBuilder().addHeader("Authorization","$YOURKEY").build()
                  chain.proceed(request)
              }*/
            //.certificatePinner(certificatePinner)

           /* .connectionSpecs(Arrays.asList(
                 ConnectionSpec.MODERN_TLS,
                 ConnectionSpec.COMPATIBLE_TLS))
             .followRedirects(true)
             .followSslRedirects(true)
             .retryOnConnectionFailure(true)
             .connectTimeout(20, TimeUnit.SECONDS)
             .readTimeout(20, TimeUnit.SECONDS)
             .writeTimeout(20, TimeUnit.SECONDS)
             .cache(null)*/
            .build();

        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("http://15.184.130.128/api/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }


        val apiServices: APIServices by lazy {
            retrofit.create(APIServices::class.java)
        }
        return retrofit
    }


    fun addPost(): APIServices {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient =
            OkHttpClient.Builder()
               .addInterceptor(interceptor)
                .connectTimeout(2, TimeUnit.MINUTES)
                .callTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()
        val apiServices= Retrofit.Builder()
            .baseUrl("http://15.184.130.128/api/user/")

            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(APIServices::class.java)
        return  apiServices
    }

}
