package com.example.projecttestapp.startPage.teacher_page.add_question_page.show_question_page.change_question_page

import android.os.Bundle
import com.example.projecttestapp.startPage.models.Question

interface ChangeQuestionContract {
    interface Model{
        interface OnFinishedListener{
            fun onFinishedUpdate()
            fun onFailure(t: Throwable)
        }
        fun updateQuestion(onFinishedListener: OnFinishedListener,question: Question)
        fun getQuestion(bundle: Bundle): Question
    }
    interface View{

    }
    interface Presenter{
        fun changeQuestion()
        fun initQuestionToView()
    }
}