package com.hyeon.moviereview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.hyeon.moviereview.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private lateinit var binding : FragmentLoginBinding
    private val navController : NavController by lazy{
        findNavController()
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

        with(binding){
            buttonLogin.setOnClickListener {
                /** 서버로 데이터 전송 */
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
}