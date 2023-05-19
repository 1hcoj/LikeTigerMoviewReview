package com.hyeon.moviereview.network

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    /** SerializedName (서버에서 변수명)
     *  앱 내 변수명 */
    @SerializedName("loginid")
    var id : String,
    @SerializedName("password")
    var password : String
) {}