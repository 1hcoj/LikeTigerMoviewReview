package com.hyeon.moviereview.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/** This is Api Client */

/** object : singleton */
object ApiClient {
    /** Http 통신 시 , 통신 내용에 대한 로그를 출력함 */
    var mHttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    /** OkHttp -> Retrofit 의 베이스 기술 */
    var mOkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(mHttpLoggingInterceptor)
        .build()
    /** Retrofit client 객체 : singleton 생성을 위한 초기화 */
    var mRetrofit : Retrofit? = null
    /** client : mRetrofit 을 생성 및 사용하기 위한 변수
     * baseurl : 기본 url
     * client : OkHttpClient 사용 ( Interceptor 사용을 위해서.. )
     * addConverterFactory() : 형식 변환을 위해 사용
     * */
    val client : Retrofit?
        get() {
            if (mRetrofit == null){
                mRetrofit = Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .client(mOkHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return mRetrofit
        }
}