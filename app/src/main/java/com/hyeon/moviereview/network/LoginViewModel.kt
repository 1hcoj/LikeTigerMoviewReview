package com.hyeon.moviereview.network

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = UserRepository()
    val loginResult : MutableLiveData<BaseResponse<LoginResponse>> = MutableLiveData()

    var userName : String? = ""
    fun loginUser(id : String, pwd : String){
        Log.d("조치현","1")
        loginResult.value = BaseResponse.Loading()

        viewModelScope.launch {
            try {
                Log.d("조치현","2")
                val loginRequest = LoginRequest(id,pwd)
                Log.d("조치현","5")
                val response = userRepo.loginUser(loginRequest)

                Log.d("조치현","3")
                /** okHttp 에 내장되어있는 code 함수 ( 통신 결과 ) */
                if (response?.code() == 200){
                    loginResult.value = BaseResponse.Success(response.body())
                } else{
                    loginResult.value = BaseResponse.Error(response?.message())
                }
            } catch (ex : java.lang.Exception){
                Log.d("조치현","4")
                loginResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}