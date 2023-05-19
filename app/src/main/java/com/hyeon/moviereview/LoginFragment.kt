package com.hyeon.moviereview

import android.content.Context
import android.os.Bundle
import android.se.omapi.Session
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.hyeon.moviereview.databinding.FragmentLoginBinding
import com.hyeon.moviereview.network.BaseResponse
import com.hyeon.moviereview.network.LoginResponse
import com.hyeon.moviereview.network.LoginViewModel
import com.hyeon.moviereview.network.SessionManager


class LoginFragment : Fragment() {
    private lateinit var binding : FragmentLoginBinding
    private val navController : NavController by lazy{
        findNavController()
    }
    private val viewModel : LoginViewModel by activityViewModels()

    private lateinit var mainActivity : MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val token = SessionManager.getToken(mainActivity)
        /** 토큰 유지.. -> 로그인 상태 유지? */
        if (!token.isNullOrBlank()){
            navigateToHome()
        }

        viewModel.loginResult.observe(mainActivity){
            when(it){
                is BaseResponse.Loading->{
                    showLoading()
                }
                is BaseResponse.Success ->{
                    stopLoading()
                    processLogin(it.data)
                }
                is BaseResponse.Error ->{
                    processError(it.msg)
                }

                else->{
                    stopLoading()
                }
            }
        }
        with(binding){
            buttonLogin.setOnClickListener {
                /** 서버로 데이터 전송 */
                doLogin()
            }
            buttonMoveToSignUp.setOnClickListener {
                /** 회원가입 페이지로 이동*/
                navController.navigate(R.id.action_loginFragment_to_signUpFragment)

            }
            buttonFindIdPwd.setOnClickListener {
                /** 아이디 비밀번호 찾기*/
            }
        }
    }

    private fun doLogin() {
        val id = binding.editTextId.text.toString()
        val pwd = binding.editTextTextPassword.text.toString()
        viewModel.loginUser(id,pwd)
    }
    /** 함수명 : processLogin
     *  매개변수 : data : LoginResponse?  ( Login Request 에 대한 응답 : 무조건 성공 )
     *  리턴값 : X
     *  함수 설명 :
     *      1. 로그인 성공한 경우에 실행됨
     *      2. Response 데이터로 받은 token ( 회원정보 포함됨 ) 을 등록함
     *      3. Home Page 로 넘어감
     * */
    private fun processLogin(data: LoginResponse?) {
        if (!data?.data?.token.isNullOrEmpty()){
            data?.data?.token?.let { SessionManager.saveAuthToken(mainActivity, it) }
            navigateToHome()
        }
    }

    private fun processError(msg: String?) {

    }

    private fun stopLoading() {

    }

    private fun showLoading() {

    }

    private fun navigateToHome() {
        navController.navigate(R.id.action_loginFragment_to_homeFragment)
    }
}
