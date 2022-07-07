package com.example.userlogindemo

import android.content.Context
import com.example.userlogindemo.Database.UsersDatabase
import com.example.userlogindemo.Database.UsersRegisterDao
import com.example.userlogindemo.Database.UsersRegisterRepo
import com.example.userlogindemo.Database.UsersRegisters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object HiltModules {
    /** Hilt Module to tell the Dagger Hilt how to inject PersonDao and PersonRespository */


    @Provides
    @Singleton
    fun providePersonDao(@ApplicationContext context: Context): UsersRegisterDao {
        return UsersDatabase.getInstance(context).getUserRegisterDao()
    }


    @Singleton
    @Provides
    fun personRepo(usersRegisterDao: UsersRegisterDao): UsersRegisterRepo {
        return UsersRegisterRepo(usersRegisterDao)
    }



}