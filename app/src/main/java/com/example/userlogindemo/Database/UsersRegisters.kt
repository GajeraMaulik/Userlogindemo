package com.example.userlogindemo.Database

import android.provider.ContactsContract
import androidx.room.Entity
import androidx.room.PrimaryKey


 data class UsersRegisters(@PrimaryKey(autoGenerate = true) var provider_app_id: Int? = 0, var username:String, var email:String, var password:String,
                           var logintype: String, var login_type:String, var first_name:String,
                           var confirm_password:String, var category:Int
)
{


}

data class UsersLogin(@PrimaryKey(autoGenerate = true) var provider_app_id: Int? = 0, var email:String,
                      var password:String, var login_type: String
)
{

}


