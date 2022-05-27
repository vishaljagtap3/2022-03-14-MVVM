package com.bitcode.mvvm1.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcode.mvvm1.models.UserModel
import com.bitcode.mvvm1.respository.UsersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(
    val usersRepository: UsersRepository
) : ViewModel() {

    private var currentPage = 0
    private var users = ArrayList<UserModel>()
    private var hasMoreData = true

    var isProcessingLiveData = MutableLiveData<Boolean>(false)

    fun getUserByPage() {
        CoroutineScope(Dispatchers.IO).launch {

            isProcessingLiveData.postValue(true)

            if(!hasMoreData) {
                isProcessingLiveData.postValue(false)
                return@launch
            }

            var usersResponseModel =
                usersRepository.getUsersByPage(++currentPage)

            withContext(Dispatchers.Main) {

                isProcessingLiveData.postValue(false)

                if(currentPage == usersResponseModel.totalPages) {
                    hasMoreData = false
                }

                Log.e("tag", usersResponseModel.toString())
                Log.e("tag", "------------------------------------------")
                users.addAll( usersResponseModel.users )
            }
        }
    }


}