package com.hyeon.moviereview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hyeon.moviereview.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {
    lateinit var binding : FragmentSignUpBinding
    /** 아이디 중복검사 */
    private var toggleForIdCheck = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding){
            buttonSignUpIdCheck.setOnClickListener {
                /** 아이디 중복 검사 */
                /** 서버에서 아이디 받아오기*/

                toggleForIdCheck = true
            }
            buttonSignUp.setOnClickListener {
                /** 회원가입 */
                if(checkSignUp()) {
                    /** 로그인 화면으로 이동*/
                }
            }
        }
    }

    private fun checkSignUp() : Boolean {
        var toggle = false
        var dialogMessage : String = ""
        val regexPwd = Regex("/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/")
        with(binding){
            if (toggleForIdCheck){
                val pwd = editTextSignupPwd.text.toString()
                val pwdCheck = editTextSignupPwdCheck.text.toString()
                val name = editTextSignupName.text.toString()
                val callNumber = editTextSignupCallnumber.text.toString()
                if (!regexPwd.matches(pwd)){
                    /** 비밀번호 형식 요청 다이얼로그*/
                    dialogMessage = "비밀번호는 영문,특수문자,숫자 조합 8~25자리 입니다."
                }
                else {
                    /** 비밀번호 확인*/
                    if (pwd != pwdCheck){
                        /** 비밀번호 불일치 다이얼로그*/
                        dialogMessage = "비밀번호가 일치하지 않습니다."

                    }else{
                        if (name.isEmpty() || callNumber.isEmpty()) {
                            /** 공백값 입력 요청 다이얼로그*/
                            dialogMessage =
                                if (name.isEmpty()) "이름을 입력해주세요"
                                else "전화번호를 입력해주세요"
                        }else {
                            /** 회원 정보 서버로 전송 */
                            toggle = true
                        }
                    }

                }
            }else {
                /** 아이디 체크 요청 다이얼로그 */
                dialogMessage = "아이디 중복검사를 해주세요."
            }
        }
        if (!toggle){
            Toast.makeText(context,dialogMessage,Toast.LENGTH_LONG).show()
        }

        return toggle
    }


}