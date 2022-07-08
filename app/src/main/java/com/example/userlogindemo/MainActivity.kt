package com.example.userlogindemo

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log.d
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.userlogindemo.GetUser.UsersActivity
import com.example.userlogindemo.Profile.FileUriUtils
import com.example.userlogindemo.Profile.ImagePicker
import com.example.userlogindemo.Profile.ImageProvider
import com.example.userlogindemo.Retrofit.APIServices
import com.example.userlogindemo.Retrofit.Client
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


var userToken: String? = null
var postPath: String? = null

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var sharedPrefManager: SharedPrefManager

    private val mMediaUri: Uri? = null
    var apiclint = Client;

    private var fileUri: Uri? = null

    private var mediaPath: String? = null

    private var mImageFileLocation = ""
    private lateinit var pDialog: ProgressDialog
    var file:File? = null

    // Image Provider
    private var imageProvider = ImageProvider.BOTH

    private var imageProviderInterceptor: ((ImageProvider) -> Unit)? = null

    private lateinit var activity: Activity

    private var mProfileUri: Uri? = null

    private val profileLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val uri = it.data?.data!!

                preview.setLocalImage(uri, true)

                postPath = FileUriUtils.getRealPath(this, uri)

                SharePref.save(this, "profile", "$uri")

                d("dvdsfgbfdb", "postpath :$postPath")

            } else parseError(it)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPrefManager = SharedPrefManager(applicationContext)
        activity = Activity()


        val username = SharePref.getStringValue(this, "username")
        userToken = SharePref.getStringValue(this, "usertoken")


        d("dvdsfgbfdb", " usertoken : $userToken")

        userName.text = username
        initDialog()
        onclick()

        logOut.setOnClickListener {
            //  SharePref.logout(this,username)
            SharePref.removeSharePref(this)
            userToken = ""
            SharePref.save(this, "null", userToken!!).toString()

            d("dvdsfgbfdb", "main  null : $userToken")


            // sharedPrefManager.clear()
            val i = Intent(this, LoginActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
            Toast.makeText(this, "singOut $username", Toast.LENGTH_LONG).show()
            finish()
        }


    }



    fun onclick() {
        pickImage.setOnClickListener {
           /* ImagePicker.with(this)
                .crop()
                .cropFreeStyle()
                .maxResultSize(512, 512, true)
                .createIntentFromDialog { profileLauncher.launch(it) }
*/

            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }

        upload.setOnClickListener {
           // uploadImage()
            api_for_addPost()
        }
        getAllPost.setOnClickListener {
            val intent = Intent(this, PostActivity::class.java)
            startActivity(intent)
        }
        getUsers.setOnClickListener {
            val intent = Intent(this, UsersActivity::class.java)
            startActivity(intent)
        }

    }





   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val selectedImageUri = data?.data

            preview.setLocalImage(selectedImageUri!!,false)

            postPath = FileUriUtils.getRealPath(this, selectedImageUri)


       //     postPath= getRealPathFromURI(selectedImageUri)
            //editText1.setText(s);
            d("imageuri  ", postPath!!)
        }
    }


    fun ImageView.setLocalImage(uri: Uri, applyCircle: Boolean = false) {
        val glide = Glide.with(this).load(uri).diskCacheStrategy(DiskCacheStrategy.NONE)
        if (applyCircle) {
            glide.apply(RequestOptions.circleCropTransform()).into(this)
        } else {
            glide.into(this)
        }
    }

    private fun getRealPathFromURI(selectedImageUri: Uri): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = managedQuery(selectedImageUri, projection, null, null, null)
        val column_index = cursor
            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        d("imageuri", "curser " + cursor + " index :" + cursor.getString(column_index))
        return cursor.getString(column_index)
    }


    fun api_for_addPost() = if(postPath == null) {
        Toast.makeText(this, "please select an image ", Toast.LENGTH_LONG).show()
            } else {
                showpDialog()
    // RequestBody post= RequestBody.create(MediaType.parse("multipart/form-data"), imagepath);
        //RequestBody thumbnail= RequestBody.create(MediaType.parse("multipart/form-data"), imagepath);
        val categories = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "1")

        //MultipartBody.Part post=null;
        //  MultipartBody.Part thumbnail=null;
        val postFile: File = File(postPath)
        val postPart: MultipartBody.Part = MultipartBody.Part.createFormData("post", postPath!!)
        val thumbnailPart: MultipartBody.Part =MultipartBody.Part.createFormData("thumbnail", postFile.name.replace("/", ""))
        val apiService: APIServices = Client.addPost()
        val call: Call<ResponseBody> = apiService.addpostData(categories, postPart, thumbnailPart)
        d("callsc", call.toString())
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                d(
                    "fsill",
                    response.code().toString() + "  " + response.body() + "  " + response.message()
                )
                if (response.isSuccessful) {
                    hidepDialog()
                    Toast.makeText(this@MainActivity, """trupartt ${response.code()}""", Toast.LENGTH_SHORT).show()
                } else {
                    hidepDialog()
                    Toast.makeText(this@MainActivity, "else part ", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                hidepDialog()
                d("fsill", t.toString() + "")
                Toast.makeText(this@MainActivity, "Api Fail   $t", Toast.LENGTH_SHORT).show()
            }
        })
    }












    // old
    fun prepareImagePart(path: String, partName: String): MultipartBody.Part {
        val file = File(path)
        val filename = file.name
      /*  val requestBody: RequestBody =
            RequestBody.create(contentResolver.getType(Uri.fromFile(file))?.toMediaType(), filename)*/
        val  requestBody: RequestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "$file")

   //     servicesEndPoint.create().updateProfile(id, fullName, body, fullName)

        return MultipartBody.Part.createFormData(partName, file.name, requestBody)
    }

/*
    private fun uploadImage() {
        if (postPath == null) {
            Toast.makeText(this, "please select an image ", Toast.LENGTH_LONG).show()
        } else {
            showpDialog()


            val file = File(postPath)
            d("dvdsfgbfdb", "before upload postpath :$postPath")

            val filename = file.name
           // filename.substring(1,filename.length-1)

            d("dvdsfgbfdb", "file name :$filename")


            val post:MultipartBody.Part = prepareImagePart(postPath!!, filename)
            val thumbnail: MultipartBody.Part = prepareImagePart(postPath!!, filename)

            val post1 = MultipartBody.Part.createFormData("post",filename)
            val thumbnail1 = MultipartBody.Part.createFormData("thumbnail",filename)
            d("dvdsfgbfdb", "---post ---> ${post1.body.contentType()}")
            d("dvdsfgbfdb", "---thumbnail---> ${thumbnail1.body.contentType()}")

            val map: MutableMap<String, Any> = HashMap()
            map.put("categories", "1,2,3")
            map.put("post", post)
            map.put("thumbnail", thumbnail)

            d("dvdsfgbfdb", "  map :$map")

            val getResponse = Client.getRetroInstance().create(APIServices::class.java)

            val call = getResponse.uploadpost("1,2,3",post,thumbnail)
            call.enqueue(object : Callback<UploadResponse> {
                override fun onResponse(
                    call: Call<UploadResponse>,
                    response: Response<UploadResponse>
                ) {
                    Log.d("dvdsfgbfdb", "body  ${response.body()}")
                    Log.d("dvdsfgbfdb", "message ${response.message()}")
                    Log.d("dvdsfgbfdb", "code ${response.code()}")
                    //     Log.d("dvdsfgbfdb", "error body  ${response.errorBody()}")

                    if (response.body()?.success!!) {
                        hidepDialog()
                        val serverResponse = response.body()
                        Toast.makeText(
                            applicationContext,
                            serverResponse?.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        d("dvdsfgbfdb", "---------->${response.body()}")
                        d("dvdsfgbfdb", "---------->${serverResponse?.message}")

                    } else {
                        hidepDialog()
                        Toast.makeText(
                            applicationContext,
                            "${response.errorBody()}",
                            Toast.LENGTH_SHORT
                        ).show()
                        d("dvdsfgbfdb", "---------->${response.errorBody()}")
                    }

                }

                override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                    hidepDialog()
                    Log.v("dvdsfgbfdb", " on failure : ${t.message}")
                }
            })
        }
    }*/

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


    override fun onStart() {
        super.onStart()

        if (!SharePref.getBooleanValue(this, "isLogin")) {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }


    private fun parseError(activityResult: ActivityResult) {
        if (activityResult.resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(activityResult.data), Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }





}


