package com.example.projecttestapp.startPage.authentication_page

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import com.example.projecttestapp.startPage.Constants
import com.example.projecttestapp.startPage.models.Student
import com.example.projecttestapp.startPage.models.Teacher
import com.example.projecttestapp.startPage.network.ApiService
import com.example.projecttestapp.startPage.network.ServiceBuilder
import kotlinx.android.synthetic.main.activity_authentication.*
import retrofit2.Call
import retrofit2.Response

class AuthenticationModel():AuthenticationContract.Model{
    private val userService = ServiceBuilder.builService(ApiService::class.java)
    override fun getFromSharedPreferences() {
    }

    override fun getStudentByLogin(onFinishedListener: AuthenticationContract.Model.OnFinishedListener,login:String,password:String) {
        val requestCall = userService.getStudentByLogin(login, null)
        requestCall.enqueue(object : retrofit2.Callback<ArrayList<Student>> {
            override fun onFailure(call: Call<ArrayList<Student>>, t: Throwable) {
                onFinishedListener.onFailure(t)
//                showToast(getString(R.string.authenticate_activity_problem_with_internet_text))
            }
            override fun onResponse(call: Call<ArrayList<Student>>, response: Response<ArrayList<Student>>) {
                onFinishedListener.onFinishedStudent(response.body()!!,response.isSuccessful)
            }
        })
    }

    override fun getTeacherByLogin(onFinishedListener: AuthenticationContract.Model.OnFinishedListener,login:String,password:String) {
        val requestCall = userService.getTeacherByLogin(login,null)
        requestCall.enqueue(object : retrofit2.Callback<ArrayList<Teacher>> {
            override fun onFailure(call: Call<ArrayList<Teacher>>, t: Throwable) {
//                showToast(getString(R.string.authenticate_activity_problem_with_internet_text))
                onFinishedListener.onFailure(t)
            }
            override fun onResponse(call: Call<ArrayList<Teacher>>, response: Response<ArrayList<Teacher>>) {
                onFinishedListener.onFinishedTeacher(response.body()!!,response.isSuccessful)
            }
        })
    }

}