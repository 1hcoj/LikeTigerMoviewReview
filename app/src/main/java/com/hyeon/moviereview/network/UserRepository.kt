package com.hyeon.moviereview.network

import android.util.Log
import retrofit2.Response

class UserRepository {
    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>?{
        Log.d("조치현","6")
        return UserApi.getApi()?.loginUser(loginRequest = loginRequest)
    }
}