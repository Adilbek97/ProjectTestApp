package com.example.projecttestapp.startPage.teacher_page.add_question_page.show_question_page

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.projecttestapp.R
import com.example.projecttestapp.startPage.Constants
import com.example.projecttestapp.startPage.adapters.ShowQuestionAdapter
import com.example.projecttestapp.startPage.models.Question
import com.example.projecttestapp.startPage.models.Subject
import com.example.projecttestapp.startPage.showToast
import kotlinx.android.synthetic.main.activity_show_question.*
import retrofit2.Call
import retrofit2.Response

class ShowQuestionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_question)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation= LinearLayoutManager.VERTICAL
        show_question_recycler_view.layoutManager = layoutManager
    }
    override fun onResume() {
        super.onResume()
        loadQuestions()
    }

    private fun loadQuestions() {
        val requestCall = Constants.userService.getQuestions()
        requestCall.enqueue(object : retrofit2.Callback<ArrayList<Question>>{
            override fun onFailure(call: Call<ArrayList<Question>>, t: Throwable) {
                showToast("We can't get quetion")
            }
            override fun onResponse(call: Call<ArrayList<Question>>, response: Response<ArrayList<Question>>) {
                val subject = intent.extras!!.getParcelable<Subject>("subject_key")
                var showQuestionAdapter: ShowQuestionAdapter = ShowQuestionAdapter(
                    this@ShowQuestionActivity,
                    sortQuestions(response.body()!!,subject.id),subject)

                show_question_recycler_view.adapter = showQuestionAdapter
            }
        })
    }
    private fun sortQuestions(questions:ArrayList<Question>,subject_id:Int):ArrayList<Question>{
        var sortedQuestions:ArrayList<Question> = ArrayList<Question>()
        questions.let {
            for (question in questions){
                if (question.subject_id.equals(subject_id)){
                    sortedQuestions.add(question)
                }
            }
        }
        return sortedQuestions
    }

}
