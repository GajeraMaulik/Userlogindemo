package com.example.userlogindemo.Database

import com.example.userlogindemo.Model.Login.Register.RegisterRequestBody
import kotlinx.coroutines.flow.Flow

class UsersRegisterRepo(val usersRegisterDao: UsersRegisterDao) {

    suspend fun insert(register: Register.Register1) {
             usersRegisterDao.insert(register)
    }
    suspend fun showData(): List<Register.Register1> {
        return usersRegisterDao.showData()
    }
  /*  suspend fun login(usename:String,password:String){
       usersRegisterDao.getSingleUserDetails(usename,password)
    }
*/

  /*  suspend fun readUser(username:String,password:String): UsersRegisters {
        return usersRegisterDao.readLoginData(username = username, password = password)
    }*/
  /*  suspend fun getAllUser(): List<UsersRegisters> {
        return usersRegisterDao .getAllPerson()
    }*/
}