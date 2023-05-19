package com.hyeon.moviereview.network

import android.util.Log
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/** Api 통신을 위한 Interface*/
interface UserApi {
    /** Post : 데이터를 @Body 에 담아 서버에 요청하는 방식
     *  주소 : Server 에서 지정한 주소
     *  @Body : 데이터를 담는 형식?
     * */
    @POST("/api/members")
    suspend fun loginUser(@Body loginRequest: LoginRequest) : Response<LoginResponse>

    companion object {
        /** UserApi Interface를 반환하는 함수
         * client? : client 는 ApiClient? 자료형을 가지는 변수 -> 그러나 반드시 null 이 아님
         * create : Retrofit 객체 생성 !! { parameter : API 통신을 위한 Interface }
         * */
        fun getApi() : UserApi? {
            Log.d("조치현","7")
            return ApiClient.client?.create(UserApi::class.java)
        }
    }
}