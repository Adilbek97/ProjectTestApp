package com.example.projecttestapp.startPage.teacher_page.add_question_page

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.projecttestapp.R
import com.example.projecttestapp.startPage.Constants
import com.example.projecttestapp.startPage.models.Question
import com.example.projecttestapp.startPage.models.Subject
import com.example.projecttestapp.startPage.showToast
import com.example.projecttestapp.startPage.teacher_page.add_question_page.show_question_page.ShowQuestionActivity
import kotlinx.android.synthetic.main.activity_add_question.*
import retrofit2.Call
import retrofit2.Response

class AddQuestionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_question)
        var bundle:Bundle = intent.extras!!
        val subject = bundle.getParcelable<Subject>("subject_key")
        val intent = Intent(this, ShowQuestionActivity::class.java)
        intent.putExtra("subject_key",subject)
        subject_name_tv.text = subject!!.name
        save_and_add_more_btn.setOnClickListener {
            var question:Question= Question()
            question.question=question_et.text.toString()
            question.optionA = optionA_et.text.toString()
            question.optionB = optionB_et.text.toString()
            question.optionC = optionC_et.text.toString()
            question.optionD = optionD_et.text.toString()
            question.correctOption = correct_option_et.text.toString()
            question.subject_id = subject.id
            addQuestion(question)
        }
        show_questions_btn.setOnClickListener {
            startActivity(intent)
        }
        addQuestion_btn.setOnClickListener {
            var question:Question= Question()
            question.question=question_et.text.toString()
            question.optionA = optionA_et.text.toString()
            question.optionB = optionB_et.text.toString()
            question.optionC = optionC_et.text.toString()
            question.optionD = optionD_et.text.toString()
            question.correctOption = correct_option_et.text.toString()
            question.subject_id = subject.id
            addQuestionandGoShowQuestion(question,intent)
        }
    }

    fun addQuestion(question: Question){
        val requestCall = Constants.userService.addQuestion(question)
        requestCall.enqueue(object : retrofit2.Callback<Question>{
            override fun onFailure(call: Call<Question>, t: Throwable) {
                showToast("Error")
            }
            override fun onResponse(call: Call<Question>, response: Response<Question>) {
                showToast("Succesfully added new question")
                startActivity(intent)
            }
        })
    }
    fun addQuestionandGoShowQuestion(question: Question,intent: Intent){
        val requestCall = Constants.userService.addQuestion(question)
        requestCall.enqueue(object : retrofit2.Callback<Question>{
            override fun onFailure(call: Call<Question>, t: Throwable) {
                showToast("Error")
            }
            override fun onResponse(call: Call<Question>, response: Response<Question>) {
                showToast("Succesfully added new question")
                startActivity(intent)
            }
        })
    }
}
