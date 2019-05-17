package com.example.projecttestapp.startPage.teacher_page.add_question_page.show_question_page.change_question_page

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.projecttestapp.startPage.models.Question
import com.example.projecttestapp.startPage.models.Teacher
import com.example.projecttestapp.startPage.showToast
import com.example.projecttestapp.startPage.teacher_page.TeacherActivity
import com.example.projecttestapp.startPage.teacher_page.add_question_page.show_question_page.ShowQuestionActivity
import kotlinx.android.synthetic.main.activity_change_question.*

class ChangeQuestionPresenter(val view: AppCompatActivity): ChangeQuestionContract.Presenter,ChangeQuestionContract.Model.OnFinishedListener{
    val bundle: Bundle = view.intent.extras!!
    val model = ChangeQuestionModel()

    override fun changeQuestion() {
        view.showToast("changeQuestion method")
        var new_question:Question= Question()
        new_question.id = model.getQuestion(bundle).id
        new_question.question = view.changeActivity_question_et.text.toString()
        new_question.optionA = view.changeActivity_optionA_et.text.toString()
        new_question.optionB = view.changeActivity_optionB_et.text.toString()
        new_question.optionC = view.changeActivity_optionC_et.text.toString()
        new_question.optionD = view.changeActivity_optionD_et.text.toString()
        new_question.correctOption = view.changeActivity_correct_option_et.text.toString()
        new_question.subject_id = model.getQuestion(bundle).subject_id
        model.updateQuestion(this,new_question)
    }
    override fun initQuestionToView() {
        val question = model.getQuestion(bundle)
        view.changeActivity_question_et.setText(question.question)
        view.changeActivity_optionA_et.setText(question.optionA)
        view.changeActivity_optionB_et.setText(question.optionB)
        view.changeActivity_optionC_et.setText(question.optionC)
        view.changeActivity_optionD_et.setText(question.optionD)
        view.changeActivity_correct_option_et.setText(question.correctOption)
    }
    override fun onFinishedUpdate(question: Question) {
        view.showToast("Question succesfully updated new value is ${question.correctOption}")
        val intent = Intent(view, ShowQuestionActivity::class.java)
        intent.putExtra("subject_key",model.getSubject(bundle))
        view.startActivity(intent)
    }
    override fun onFailure(t: Throwable) {
        view.showToast("updating failed")
    }
}