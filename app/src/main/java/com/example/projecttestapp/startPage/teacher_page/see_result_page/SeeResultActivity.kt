package com.example.projecttestapp.startPage.teacher_page.see_result_page

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.projecttestapp.R

class SeeResultActivity : AppCompatActivity(),SeeResultContract.View {
    lateinit var mPresenter: SeeResultPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_result)
        mPresenter = SeeResultPresenter(this)
        mPresenter.seeResult()
    }
    override fun onFailureMessage() {

    }
}
