package com.bitcode.mvvm1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bitcode.mvvm1.databinding.ActivityMainBinding
import com.bitcode.mvvm1.models.UserPostModel
import com.bitcode.mvvm1.network.ApiService
import com.bitcode.mvvm1.respository.UsersRepository
import com.bitcode.mvvm1.viewmodels.UserViewModel
import com.bitcode.mvvm1.viewmodels.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    //var usersRepository  = UsersRepository(ApiService.getInstance())
    private lateinit var userViewModel: UserViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //3
        initViewModels()
        initObservers()


        binding.btnFetchUsers.setOnClickListener {
            userViewModel.getUserByPage()
            it.isEnabled = false
        }


        //2
        /*CoroutineScope(Dispatchers.IO).launch {
            var usersResponseModel =
                usersRepository.getUsersByPage(2)

            withContext(Dispatchers.Main) {
                mt(usersResponseModel.toString())
            }
        }*/

        //1
        /*CoroutineScope(Dispatchers.IO).launch {
            var usersResponseModel =
                ApiService.getInstance().getUsers(1)

            *//*var userPostResponseModel =
                ApiService.getInstance().addUser("Vishal", "Trainer")*//*

            var userPostResponseModel =
                ApiService.getInstance().addUserObject(
                    UserPostModel("Vishal Jagtap", "Dev")
                )

            withContext(Dispatchers.Main) {
                mt(usersResponseModel.toString())
                mt("----------------------------------")
                mt(userPostResponseModel.toString())
            }
        }*/

    }

    private fun initObservers() {
        userViewModel.isProcessingLiveData.observe(
            this
        ) {
            if(!it) {
                binding.btnFetchUsers.isEnabled = true
            }
        }
    }

    private fun initViewModels() {
        userViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                UsersRepository(
                    ApiService.getInstance()
                )
            )
        ).get(UserViewModel::class.java)
    }

    private fun mt(text : String) {
        Log.e("tag", text)
    }
}