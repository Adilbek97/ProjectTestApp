package com.example.projecttestapp.startPage.register_page

import android.content.SharedPreferences
import com.example.projecttestapp.startPage.Constants
import com.example.projecttestapp.startPage.models.Student
import com.example.projecttestapp.startPage.models.Teacher
import com.example.projecttestapp.startPage.network.ApiService
import com.example.projecttestapp.startPage.network.ServiceBuilder
import retrofit2.Call
import retrofit2.Response

class RegisterModel(sharedPreferences: SharedPreferences) :RegisterContract.Model {
    private val userService = ServiceBuilder.builService(ApiService::class.java)
    var sharedPreferences: SharedPreferences? = sharedPreferences

    override fun addTeacher(onFinishedListener: RegisterContract.Model.OnFinishedListener, teacher: Teacher) {
        val requestCall = userService.addTeacher(teacher)
        requestCall.enqueue(object : retrofit2.Callback<Teacher>{
            override fun onFailure(call: Call<Teacher>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }
            override fun onResponse(call: Call<Teacher>, response: Response<Teacher>) {
                putTeacherToSharedPreferences(response.body()!!)
                onFinishedListener.onFinishedTeacher(response.body()!!)
            }
        })
    }


    override fun addStudent(onFinishedListener: RegisterContract.Model.OnFinishedListener,student: Student) {
        val requestCall = userService.addStudent(student)
        requestCall.enqueue(object : retrofit2.Callback<Student>{
            override fun onFailure(call: Call<Student>, t: Throwable) {
                onFinishedListener.onFailure(t)
//                show_errors_tv.text = getString(R.string.register_activity_cant_add_text)
            }
            override fun onResponse(call: Call<Student>, response: Response<Student>) {
                putStudentToSharedPreferences(response.body()!!)
                onFinishedListener.onFinishedStudent(response.body()!!)
            }
        })
    }

    override fun putTeacherToSharedPreferences(teacher: Teacher) {
        val shared_preferences_editor: SharedPreferences.Editor = sharedPreferences!!.edit()
        if(!sharedPreferences!!.contains(Constants.APP_PREFERENCES_KEY)){
            shared_preferences_editor.putInt(Constants.APP_PREFERENCES_KEY,teacher.id)
            shared_preferences_editor.putString(Constants.APP_PREFERENCES_LOGIN,teacher.login)
            shared_preferences_editor.putString(Constants.APP_PREFERENCES_PASSWORD,teacher.password)
            shared_preferences_editor.apply()
        }else{
            if(teacher.id != sharedPreferences!!.getInt(Constants.APP_PREFERENCES_KEY,0)){
                shared_preferences_editor.putInt(Constants.APP_PREFERENCES_KEY,teacher.id)
                shared_preferences_editor.putString(Constants.APP_PREFERENCES_LOGIN,teacher.login)
                shared_preferences_editor.putString(Constants.APP_PREFERENCES_PASSWORD,teacher.password)
                shared_preferences_editor.apply()
            }
        }
    }

    override fun putStudentToSharedPreferences(student: Student) {
        val shared_preferences_editor: SharedPreferences.Editor = sharedPreferences!!.edit()
        if(!sharedPreferences!!.contains(Constants.APP_PREFERENCES_KEY)){
            shared_preferences_editor.putInt(Constants.APP_PREFERENCES_KEY,student.id)
            shared_preferences_editor.putString(Constants.APP_PREFERENCES_LOGIN,student.login)
            shared_preferences_editor.putString(Constants.APP_PREFERENCES_PASSWORD,student.password)
            shared_preferences_editor.apply()
        }else{
            if(!student.id.equals(sharedPreferences!!.getInt(Constants.APP_PREFERENCES_KEY,0))){
                shared_preferences_editor.putInt(Constants.APP_PREFERENCES_KEY,student.id)
                shared_preferences_editor.putString(Constants.APP_PREFERENCES_LOGIN,student.login)
                shared_preferences_editor.putString(Constants.APP_PREFERENCES_PASSWORD,student.password)
                shared_preferences_editor.apply()
            }
        }
    }
}