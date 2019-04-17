package com.example.projecttestapp.startPage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.projecttestapp.R
import com.example.projecttestapp.startPage.student_page.StudentActivity
import com.example.projecttestapp.startPage.teacher_page.TeacherActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mPresenter:StartPagePresenter = StartPagePresenter(this)
        teacher_btn.setOnClickListener {
            mPresenter.setStatus("teacher")
            mPresenter.goToOtherActivity()
        }
        student_btn.setOnClickListener {
            mPresenter.setStatus("student")
            mPresenter.goToOtherActivity()
        }
    }
}
