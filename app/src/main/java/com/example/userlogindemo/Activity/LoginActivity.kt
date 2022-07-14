package com.example.userlogindemo.Activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.Log.d
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.userlogindemo.Database.UsersRegisterRepo
import com.example.userlogindemo.Model.Login.Register.Login.Login
import com.example.userlogindemo.Model.Login.Register.Login.LoginRequestBody
import com.example.userlogindemo.R
import com.example.userlogindemo.Retrofit.APIServices
import com.example.userlogindemo.Retrofit.Client
import com.example.userlogindemo.SharePref
import com.example.userlogindemo.Viewmodel.RegisterViewModel
import com.example.userlogindemo.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var username: String
    private lateinit var password: String
    private var prg: ProgressDialog? = null
    private  lateinit var registerViewModel: RegisterViewModel
    lateinit var usersRegisterRepo: UsersRegisterRepo
   // lateinit var usersRegisterDao: UsersRegisterDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

       val actionBar = supportActionBar
       actionBar!!.hide()
       actionBar.setDisplayHomeAsUpEnabled(true)
       window.statusBarColor = ContextCompat.getColor(this, R.color.colorAccent)


        init()

        listener()

    }


  private fun init() {


       // createUserViewModel = CreateUserViewModel()
       // usersRegisterRepo = UsersRegisterRepo(usersRegisterDao)
        //registerViewModel = RegisterViewModel(usersRegisterRepo)


        prg = ProgressDialog(this)

        etUser.setBackgroundResource(R.drawable.edittext_selector)
        etPassword.setBackgroundResource(R.drawable.edittext_selector)

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

        forgotPassword.setOnClickListener {
            if (etUser.text.toString().isEmpty()) {
                Toast.makeText(applicationContext, "Enter your Email", Toast.LENGTH_SHORT).show()
                etUser.requestFocus()
            } else {
                Toast.makeText(applicationContext, "Email Sent", Toast.LENGTH_SHORT).show()
            }
        }
      signupBtn.setOnClickListener {
          val intent = Intent(baseContext, RegisterActivity::class.java)
          startActivity(intent)
          // overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
          finish()
      }
    }



    private fun listener() {

        val buttonClick = AlphaAnimation(1f, 0.8f)
        signinBtn.setOnClickListener(View.OnClickListener { v: View ->
            etUser.clearFocus()
            etPassword.clearFocus()
            v.startAnimation(buttonClick)
            dismissKeyboard()
            username = etUser.text.toString().trim()
            password = etPassword.text.toString()

            if(username.isEmpty()){
                etUser.error = "Email required"
                etUser.requestFocus()
            }else if(password.isEmpty()){
                etPassword.error = "Password required"
               etPassword.requestFocus()
            }else{
               logInUser()
            }

        })


    }


    fun logInUser() {
        d("dvdsfgbfdb","api hghgn}")




        val user  = LoginRequestBody(0, 123, etUser.text.toString(), etPassword.text.toString(), "email")

    /*    val logindata = Data("", userToken!!,"","",username,"","male","",0,0,0,0,""
        ,"email","","","","", ArrayList<UserCategory>(),username)
      registerViewModel.login(username,password)*/

        val retroInstance = Client.getRetroInstance().create(APIServices::class.java)
        val call = retroInstance.getlogin(user)
        call.enqueue(object : Callback<Login> {

            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                Log.d("dvdsfgbfdb", "api before  if ${response.code()}")
                Log.d("dvdsfgbfdb", "message ${ response.body()}")


                if(response.body()?.success!!) {

                    Log.d("dvdsfgbfdb", "api after if ${response.code()}")
                    Log.d("dvdsfgbfdb", "after message ${response.message()}")
                    Log.d("dvdsfgbfdb", "api  if true")

                    SharePref.save(this@LoginActivity, "username", username)
                    SharePref.save(
                        this@LoginActivity,
                        "usertoken",
                        "${response?.body()!!.data.access_token}"
                    )

                    Log.d("dvdsfgbfdb", "token : ${response?.body()!!.data.access_token}")
                    //SharedPrefManager.getInstance(applicationContext).saveUser(response.body()!!.login)

                    Toast.makeText(this@LoginActivity, "Login success!", Toast.LENGTH_SHORT).show()

                    val intent =Intent(this@LoginActivity, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)

                    SharePref.save(this@LoginActivity, "isLogin", true)
                    //  registerViewModel.insert()

                } else {
                    Log.d("dvdsfgbfdb", "else ${response.body()?.message}")
                    //createNewUsersLiveData.postValue(null)
                    Toast.makeText(this@LoginActivity,response.body()?.message, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Login>, t: Throwable) {
                //   createNewUsersLiveData.postValue(null)
                Toast.makeText(this@LoginActivity,t.message, Toast.LENGTH_SHORT).show()
                d("dvdsfgbfdb","--login error->$t")
            }
        })




    }

    override fun onStart() {
        super.onStart()

        if(SharePref.getBooleanValue(this, "isLogin")){
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }

    fun dismissKeyboard() {
        val imm = (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
        imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
    }


}