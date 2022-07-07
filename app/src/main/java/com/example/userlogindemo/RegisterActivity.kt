package com.example.userlogindemo

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.Log.d
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.example.userlogindemo.Database.*
import com.example.userlogindemo.Model.Login.Register.RegisterRequestBody
import com.example.userlogindemo.Model.Login.Register.Registration
import com.example.userlogindemo.Profile.ImagePicker
import com.example.userlogindemo.Profile.setLocalImage
import com.example.userlogindemo.Retrofit.APIServices
import com.example.userlogindemo.Retrofit.Client
import com.example.userlogindemo.Viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    var prg : ProgressDialog? = null
    val viewModel: RegisterViewModel by viewModels()
   var registerViewModel: RegisterViewModel?=null
    var usersRegisterRepo: UsersRegisterRepo?=null
   lateinit var usersRegisterDao: UsersRegisterDao
    private lateinit var email: String
    private lateinit var password : String
    private lateinit var username: String


    private var mProfileUri: Uri? =null

    private val profileLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK ) {
            val uri= it.data?.data!!
            mProfileUri = uri
            Log.d("TAG","Profile:$uri")
            imgProfile.setLocalImage(uri, true)
            SharePref.save(this,"profile","$uri")
            //uploadImage(uri)

        } else parseError(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val actionBar = supportActionBar
        actionBar!!.hide()
        actionBar.setDisplayHomeAsUpEnabled(true)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorAccent)

        init()
        listeners()
    }

    private fun init() {
        etEmailUp.setBackgroundResource(R.drawable.edittext_selector)
        etUserName.setBackgroundResource(R.drawable.edittext_selector)
        etPassword.setBackgroundResource(R.drawable.edittext_selector)

      /*  usersRegisterDao =
        usersRegisterRepo = UsersRegisterRepo(usersRegisterDao)
        registerViewModel = RegisterViewModel(usersRegisterRepo)
*/

        prg = ProgressDialog(this)
        var isVisiblePassword = false
        ivEye.setOnClickListener {
            if (!isVisiblePassword) {
                etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                ivEye.setImageResource(R.drawable.ic_visibility_on_eye)

                isVisiblePassword = true
            } else {
                etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                ivEye.setImageResource(R.drawable.ic_visibility_off_eye)
                isVisiblePassword = false
            }
            etPassword.setSelection(etPassword.text.length)
        }
        signinBtnUp.setOnClickListener {
            val intent = Intent(baseContext, LoginActivity::class.java)
            startActivity(intent)
            // overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
            finish()
        }
     //   return usersRegisterDao
    }

    fun listeners() {
        registerBtn.setOnClickListener {

            username = etUserName.text.toString().trim()
            email  = etEmailUp.text.toString().trim()
            password = etPassword.text.toString().trim()
         if (username.isEmpty()) {
                etUserName.error = "Please enter a username."
                etUserName.requestFocus()
            } else if (email.isEmpty()) {
                etEmailUp.error = "Please enter your Email Id."
                etEmailUp.requestFocus()
            } else if (password.isEmpty()) {
                etPassword.error = "Please set your password."
                etPassword.requestFocus()
            } else {

                dismissKeyboard()
                signUpUsers()
            }
            //isValid()
        }

    }
   private fun signUpUsers() {

        val  user  = RegisterRequestBody(
            id = 0,
            provider_app_id = 123,
            login_type = "email",
            last_name = "Yash",
            first_name = "Yash",
            password = etPassword.text.toString().trim(),
            confirm_password = etPassword.text.toString().trim(),
            email = etEmailUp.text.toString().trim(),
            username = etUserName.text.toString().trim()
        )

       val registeruser = Register.Register1(
           id = 0,
           provider_app_id = 123,
           login_type = "email",
           last_name = "Yash",
           first_name = "Yash",
           password = etPassword.text.toString().trim(),
           confirm_password = etPassword.text.toString().trim(),
           email = etEmailUp.text.toString().trim(),
           username = etUserName.text.toString().trim()

       )
    //  val user1 = UserRegisterData(0,123,"email","a","a",email,username,password,password)

       //createUserViewModel = CreateUserViewModel()
     //  createUserViewModel!!.registerUser(user)
     //  registerViewModel?.insert(user)


          val call = Client.getRetroInstance().create(APIServices::class.java)
           call.getregister(user).enqueue(object :Callback<Registration>{
               override fun onResponse(
                   call: Call<Registration>,
                   response: Response<Registration>
               ) {
                   Log.d("dvdsfgbfdb", "message ${     response.code()}")
                   Log.d("dvdsfgbfdb", "message ${     response.message()}")

                   if (response.body()?.success!!) {


                       Toast.makeText(this@RegisterActivity, "Registration success!", Toast.LENGTH_SHORT)
                           .show()
                     //  val data = response.body()?.register?.getRegister()

                       startActivity(Intent(this@RegisterActivity,LoginActivity::class.java))

                       viewModel.insert(registeruser)

                    //   d("showdata","showdata $data")

                       d("abc123","---------->${response.body()}")

                   } else {
                       Toast.makeText(this@RegisterActivity, "${response.errorBody()}", Toast.LENGTH_SHORT)
                           .show()
                       Log.d("dvdsfgbfdb", "message ${     response.errorBody()}")
                   }
               }

               override fun onFailure(call: Call<Registration>, t: Throwable) {
                   Toast.makeText(this@RegisterActivity, "error : $t", Toast.LENGTH_SHORT)
                       .show()
                   d("dvdsfgbfdb","register error : $t")
               }

           })
   }


//    fun pickProfileImage(view: View) {
//        ImagePicker.with(this)
//            .crop()
//            .cropOval()
//            .cropFreeStyle()
//            .maxResultSize(512, 512, true)
//            .createIntentFromDialog { profileLauncher.launch(it) }
//    }
//    private fun uploadImage(fileuri:Uri?){
//        val progress = ProgressDialog(this)
//        progress.setMessage("Uploading File ...")
//        progress.setCancelable(false)
//        progress.show()
//
//        val pfilePath: String = File("$mProfileUri").name
//
//        Log.d("TAG","p$pfilePath")
//
//        val formatter = SimpleDateFormat("dd-mm", Locale.getDefault())
//        val now = Date()
//        val filename = formatter.format(now).toString() + ".jpeg"
//       // val storageReference = FirebaseStorage.getInstance().getReference("Images/$filename")
//
//       /* mProfileUri?.let {
//            storageReference.putFile(it).addOnSuccessListener {
//                imgProfile.setImageURI(null)
//                Toast.makeText(this,"Successfully uploaded",Toast.LENGTH_LONG).show()
//                if (progress.isShowing) progress.dismiss()
//            }.addOnFailureListener{
//                if (progress.isShowing) progress.dismiss()
//                Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show()
//            }
//        }*/
//    }
    private fun parseError(activityResult: ActivityResult) {
        if (activityResult.resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(activityResult.data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    fun dismissKeyboard() {
        val imm = (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
        imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
    }




}
