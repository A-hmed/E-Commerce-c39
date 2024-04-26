package com.mis.route.e_commerce.ui.activities.auth.fragments.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.mis.route.e_commerce.R
import com.mis.route.e_commerce.databinding.FragmentLoginBinding
import com.mis.route.e_commerce.ui.activities.home.HomeActivity
import com.mis.route.e_commerce.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    private val viewModel: LoginViewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun observeLiveData() {
        super.observeLiveData()
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            Log.e("BaseFragment", "isLoadingLiveData: $it")
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        }
        viewModel.viewMessageLiveData.observe(viewLifecycleOwner) {
            showDialog(
                it.title,
                it.message,
                it.posButtonTitle,
                it.negButtonTitle,
                it.onPosButtonClick,
                it.onNegButtonClick
            )
        }
        viewModel.events.observe(viewLifecycleOwner) {
            it?.let {
                when (it) {
                    LoginEvents.SuccessfulLogin -> {
                        startActivity(Intent(activity, HomeActivity::class.java))
                    }
                }
            }
        }
    }
}