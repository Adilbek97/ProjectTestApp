package com.example.projecttestapp.startPage.teacher_page.select_subject_page

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.projecttestapp.R

class SelectSubjectActivity : AppCompatActivity(),SelectSubjectContract.View {
    lateinit var mPresenter:SelectSubjectPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_subject)
        mPresenter = SelectSubjectPresenter(this)
        setDataToRecyclerView()
    }

    override fun setDataToRecyclerView() {
        mPresenter.settingRecyclerView()
    }
}
