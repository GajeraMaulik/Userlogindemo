package com.example.userlogindemo.Database

import androidx.room.*
import com.example.userlogindemo.Model.Login.Register.RegisterRequestBody
import kotlinx.coroutines.flow.Flow

@Dao
 interface UsersRegisterDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(register: Register.Register1)

     @Query("SELECT * FROM UserRegister")
    suspend fun showData(): List<Register.Register1>

 }
