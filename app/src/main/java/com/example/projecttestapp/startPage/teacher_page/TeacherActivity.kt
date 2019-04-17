package com.example.projecttestapp.startPage.teacher_page

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.projecttestapp.R
import kotlinx.android.synthetic.main.activity_teacher.*

class TeacherActivity : AppCompatActivity() {
    lateinit var mPresenter:TeacherPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher)
        mPresenter = TeacherPresenter(this)
        see_results_btn.setOnClickListener {
            mPresenter.goToSeeResults()
        }
        add_questions_btn.setOnClickListener {
            mPresenter.goToAddQuestions()
        }
    }

}
