package com.hyeon.moviereview.network

import com.google.gson.annotations.SerializedName

/* Todo : 서버로 로그인 요청 시 응답데이터 양식 알아오기
*  Todo : 서버의 response 데이터 타입 수정하기
*
* Spring 에서 cookie 자동으로 넣어서 보냄...
* Session 저장소
* */
data class LoginResponse(
    @SerializedName("name")
    var name : String,
    // ` ` ( backtick ) : 특수 문자나 예약어와 같은 식별자를 사용 가능함
    /* @SerializedName("data")
    var `data` : Data */
    @SerializedName("Token")
    var token : String
) {
    data class Data(
        @SerializedName("Token")
        var token : String
    )
}