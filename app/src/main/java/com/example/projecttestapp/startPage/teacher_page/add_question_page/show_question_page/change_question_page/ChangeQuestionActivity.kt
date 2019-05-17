package com.example.projecttestapp.startPage.teacher_page.add_question_page.show_question_page.change_question_page

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.projecttestapp.R
import kotlinx.android.synthetic.main.activity_change_question.*

class ChangeQuestionActivity : AppCompatActivity() {
    lateinit var presenter: ChangeQuestionPresenter
    companion object {
        val TAG:String = "ChangeQuestionActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_question)
        presenter = ChangeQuestionPresenter(this)
        presenter.initQuestionToView()
        changeActivity_UpdateQuestion_btn.setOnClickListener {
            presenter.changeQuestion()
        }
    }
}
