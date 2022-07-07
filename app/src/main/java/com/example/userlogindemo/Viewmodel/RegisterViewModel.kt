package com.example.userlogindemo.Viewmodel

import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userlogindemo.Database.*
import com.example.userlogindemo.Model.Login.Register.RegisterRequestBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(private val usersRegisterRepo: UsersRegisterRepo): ViewModel() {
    private val _list = MutableLiveData<List<Register.Register1>>()
    val list: LiveData<List<Register.Register1>> = _list

    fun insert(register: Register.Register1) = viewModelScope.launch {
        usersRegisterRepo.insert(register)

        val data =_list.postValue(usersRegisterRepo.showData())

        d("showdata","showdata $data")
    }
    fun showData() = viewModelScope.launch {
        _list.postValue(usersRegisterRepo.showData())
    }


   /*  fun login(username:String, password:String) = viewModelScope.launch{
        usersRegisterRepo1.login(username,password)
    }*/

    /*fun readUser(username:String,password:String) = viewModelScope.launch {
        usersRegisterRepo1.readUser(username = username, password = password)
    }*/






}


