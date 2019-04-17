package com.example.projecttestapp.startPage.register_page

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.projecttestapp.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    lateinit var mPresenter: RegisterPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mPresenter = RegisterPresenter(this)

        register_btn.setOnClickListener {
            mPresenter.register()
        }
    }
}
