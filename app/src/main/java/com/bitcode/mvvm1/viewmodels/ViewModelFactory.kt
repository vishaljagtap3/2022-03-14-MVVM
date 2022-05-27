package com.bitcode.mvvm1.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bitcode.mvvm1.respository.UsersRepository

class ViewModelFactory(val usersRepository : UsersRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(usersRepository) as T
        }

        throw UnknownError()
    }
}