package com.example.projecttestapp.startPage.teacher_page.see_result_page

import android.os.Bundle
import com.example.projecttestapp.startPage.models.Student
import com.example.projecttestapp.startPage.models.Subject
import com.example.projecttestapp.startPage.network.ApiService
import com.example.projecttestapp.startPage.network.ServiceBuilder
import retrofit2.Call
import retrofit2.Response

class SeeResultModel :SeeResultContract.Model {
    private val userService = ServiceBuilder.builService(ApiService::class.java)
    override fun getSubject(bundle: Bundle): Subject {
        return bundle.getParcelable("subject_key") as Subject
    }

    override fun getStudents(onFinishedListener: SeeResultContract.Model.OnFinishedListener, bundle: Bundle) {
        val requestCall = userService.getStudents()
        requestCall.enqueue(object : retrofit2.Callback<ArrayList<Student>> {
            override fun onFailure(call: Call<ArrayList<Student>>, t: Throwable) {
//                showToast("We cant get students . Problem with Internet or something doing uncorrect")
                onFinishedListener.onFailure(t)
            }
            override fun onResponse(call: Call<ArrayList<Student>>, response: Response<ArrayList<Student>>) {
                onFinishedListener.onFinishedStudent(response.body()!!,response.isSuccessful)

            }
        })
    }

//    fun getSubject(id: Int) {
//        val requestCall = userService.getSubject(id)
//        requestCall.enqueue(object : retrofit2.Callback<Subject> {
//            override fun onFailure(call: Call<Subject>, t: Throwable) {
//                itemView.subject_tv.text = "unknown subject"
//            }
//            override fun onResponse(call: Call<Subject>, response: Response<Subject>) {
//                itemView.subject_tv.text = response.body()!!.name
//            }
//        })
//    }
}