package com.hyeon.moviereview.network

/** sealed class : 추상 클래스 ( 컴파일러가 자식 Class 의 종류를 모두 알 수 있도록 한다.... ) -> 기본적으로 instance 생성이 불가 ( 추상 클래스 이므로 )
 *  BaseResponse 의 역할 : 서버와의 통신에 대한 결과 ( 성공 / 실패 여부, 로딩 ) 을 나타내는 클래스의 부모 클래스
 *  out 키워드의 역할 :
 *  nothing 자료형의 의미 : Nothing ( no 객체 , 어떠한 값도 가질 수 없음 )
 *      -> 왜 사용 하는가 ? :
 * */
sealed class BaseResponse<out T> {
    data class Success<out T> (val data : T? = null) : BaseResponse<T>()
    data class Loading(val nothing : Nothing? = null) : BaseResponse<Nothing>()
    data class Error(val msg : String?) : BaseResponse<Nothing>()
}