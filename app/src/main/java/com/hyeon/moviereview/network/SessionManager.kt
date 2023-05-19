package com.hyeon.moviereview.network

import android.content.Context
import android.content.SharedPreferences
import com.hyeon.moviereview.R
/** Todo : Session 을 시간을 통해 만료하거나 재발급 하는 Manage 방식을 추가
 * */
object SessionManager {
    // 저장공간에서 Token 을 찾기위한 Key value
    const val USER_TOKEN = "user_token"

    fun saveAuthToken(context : Context, token : String) {
        saveString(context, USER_TOKEN,token)
    }
    /** 토근을 반환하는 함수 */
    fun getToken(context : Context) : String? {
        return getString(context, USER_TOKEN)
    }
    /** 저장 공간에서 Token 문자열을 받아오는 함수 */
    private fun getString(context: Context, key: String): String? {
        val prefs : SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE)
        return prefs.getString(this.USER_TOKEN,null)
    }
    /** Login 시 : Token 을 저장 공간에 저장하는 함수 */
    private fun saveString(context: Context, key: String, value: String) {
        val prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(key,value)
        editor.apply()
    }
    /** Logout 시 : Token 을 저장 공간에서 삭제하는 함수 */
    fun clearData(context: Context){
        val editor = context.getSharedPreferences(context.getString(R.string.app_name),
        Context.MODE_PRIVATE).edit()
        editor.clear()
        editor.apply()
    }
}