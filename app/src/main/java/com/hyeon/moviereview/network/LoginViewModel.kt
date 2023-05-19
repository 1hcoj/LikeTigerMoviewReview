package com.hyeon.moviereview.network

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = UserRepository()
    val loginResult : MutableLiveData<BaseResponse<LoginResponse>> = MutableLiveData()

    fun loginUser(id : String, pwd : String){

        loginResult.value = BaseResponse.Loading()

        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(id,pwd)
                val response = userRepo.loginUser(loginRequest)
                /** okHttp 에 내장되어있는 code 함수 ( 통신 결과 ) */
                if (response?.code() == 200){
                    loginResult.value = BaseResponse.Success(response.body())
                } else{
                    loginResult.value = BaseResponse.Error(response?.message())
                }
            } catch (ex : java.lang.Exception){
                loginResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}