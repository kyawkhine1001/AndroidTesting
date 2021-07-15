package com.kkk.androiduiunittesting.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.kkk.androiduiunittesting.R
import com.kkk.androiduiunittesting.model.networkRequest.RegisterRequest
import com.kkk.androiduiunittesting.model.uimodel.RegisterUIModel
import com.kkk.androiduiunittesting.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val mViewModel: MainViewModel by viewModel()
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btnRegister.setOnClickListener {
            val fullName = edtName.text.toString()
            val phoneNumber = edtPhone.text.toString()
            val password = edtPassword.text.toString()
            when {
                fullName.isNullOrEmpty() -> {
                    showAlert(getString(R.string.fullNameErrorMessage))
                }
                phoneNumber.isNullOrEmpty() -> {
                    showAlert(getString(R.string.phoneErrorMessage))
                }
                password.isNullOrEmpty() -> {
                    showAlert(getString(R.string.passwordErrorMessage))
                }
                else -> {
                    showLoadingView()
                    mViewModel.makeRegistration(fullName,phoneNumber,password)
                }
            }
        }
        mViewModel.registerUIData.observe(this, Observer<RegisterUIModel> {
            hideLoadingView()
            it.data?.let {userProfileData ->
//                showAlert(getString(R.string.registerSuccessMessage))
                if (userProfileData.fullName != null && userProfileData.phone != null){
                    startActivity(HomeActivity.getIntent(this,userProfileData.fullName!!,userProfileData.phone!!))
                }
            }
            it.error?.let {
                showAlert(it)
            }
        })
    }
}