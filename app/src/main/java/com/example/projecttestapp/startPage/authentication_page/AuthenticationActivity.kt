package com.example.projecttestapp.startPage.authentication_page

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.projecttestapp.R
import kotlinx.android.synthetic.main.activity_authentication.*

class AuthenticationActivity : AppCompatActivity(),AuthenticationContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        val mPresenter = AuthenticationPresenter(this)
        mPresenter.getFromSharedPreferences()
        SignIn_btn.setOnClickListener {
            mPresenter.login()
        }
        text_or_register_now.setOnClickListener {
            mPresenter.goToRegister()
        }

    }
}
