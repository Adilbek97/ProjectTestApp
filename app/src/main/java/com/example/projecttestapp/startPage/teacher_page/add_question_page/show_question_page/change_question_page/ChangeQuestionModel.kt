package com.example.projecttestapp.startPage.teacher_page.add_question_page.show_question_page.change_question_page

import android.os.Bundle
import android.util.Log
import com.example.projecttestapp.startPage.models.Question
import com.example.projecttestapp.startPage.models.Subject
import com.example.projecttestapp.startPage.network.ApiService
import com.example.projecttestapp.startPage.network.ServiceBuilder
import retrofit2.Call
import retrofit2.Response

class ChangeQuestionModel :ChangeQuestionContract.Model {
    private val userService = ServiceBuilder.builService(ApiService::class.java)
    override fun updateQuestion(onFinishedListener: ChangeQuestionContract.Model.OnFinishedListener,question: Question) {
        Log.i(ChangeQuestionActivity.TAG,"model UpdateQuestion")
        val requestCall = userService.updateQuestion(question.id,question)
        requestCall.enqueue(object : retrofit2.Callback<Question>{
            override fun onFailure(call: Call<Question>, t: Throwable) {
                Log.i(ChangeQuestionActivity.TAG,"onFailure")
                onFinishedListener.onFailure(t)
            }
            override fun onResponse(call: Call<Question>, response: Response<Question>) {
                Log.i(ChangeQuestionActivity.TAG,"onresponse ")
                onFinishedListener.onFinishedUpdate(response.body()!!)
                Log.i(ChangeQuestionActivity.TAG," new cor_option ${response.body()!!.correctOption}")
            }
        })
    }

    override fun getQuestion(bundle: Bundle): Question {
        return bundle.getParcelable("currentQuestionKey") as Question
    }
    fun getSubject(bundle: Bundle): Subject {
        return bundle.getParcelable("subject_key") as Subject
    }
}