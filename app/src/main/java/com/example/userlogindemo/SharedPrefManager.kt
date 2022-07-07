package com.example.userlogindemo

import android.annotation.SuppressLint
import android.content.Context
import com.example.userlogindemo.Model.Login.Register.Login.LoginRequestBody

class SharedPrefManager(val mCtx: Context) {

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("id", -1) != -1
        }

    val user: LoginRequestBody
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return LoginRequestBody(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getInt("provider_app_id", -1),
                sharedPreferences.getString("email", null)!!,
                sharedPreferences.getString("password", null)!!,
                sharedPreferences.getString("login_type", null)!!
            )
        }


    fun saveUser(user: LoginRequestBody) {

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        //editor.putInt("id",user!!.id)
        editor.putInt("provider_app_id", user.provider_app_id)
        editor.putString("email", user.email)
        editor.putString("password", user.password)
        editor.putString("login_type", user.login_type)

        editor.apply()

    }

    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private val SHARED_PREF_NAME = "my_shared_preff"
        @SuppressLint("StaticFieldLeak")
        private var mInstance: SharedPrefManager? = null
        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }

}