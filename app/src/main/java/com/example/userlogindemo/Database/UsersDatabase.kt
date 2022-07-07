package com.example.userlogindemo.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Register.Register1::class], version = 1, exportSchema = false)
abstract class UsersDatabase:RoomDatabase() {
    private val INSTANCE: Database? = null

    companion object {
        fun getInstance(context: Context): UsersDatabase {
                return Room.databaseBuilder(context, UsersDatabase::class.java, "UsersDb")
                    .fallbackToDestructiveMigration().build()
            }
    }

    abstract fun getUserRegisterDao(): UsersRegisterDao

}