package com.hyeon.moviereview

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.hyeon.moviereview.databinding.FragmentHomeBinding
import com.hyeon.moviereview.network.BaseResponse
import com.hyeon.moviereview.network.LoginResponse
import com.hyeon.moviereview.network.LoginViewModel
import com.hyeon.moviereview.network.SessionManager


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val viewModel: LoginViewModel by activityViewModels()
    lateinit var mainActivity: MainActivity
    private val navController : NavController by lazy{
        findNavController()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /** 일단은 껏다가 키면 안됨 -> 껐다가 다시 킬 때, DB 에서 가져오는 과정 필요 !! */
        viewModel.userName?.let{
            binding.textView1.text = it + "님 환영합니다."
        }
//        binding.textView1.text = viewModel.userName + "님 환영합니다."

        binding.buttonLogOut.setOnClickListener {
            SessionManager.clearData(mainActivity)
            navController.navigate(R.id.action_homeFragment_to_loginFragment)
        }
    }
}