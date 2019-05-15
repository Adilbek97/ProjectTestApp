package com.example.projecttestapp.startPage.teacher_page

import android.content.SharedPreferences
import com.example.projecttestapp.startPage.Constants
import com.example.projecttestapp.startPage.models.Teacher
import com.example.projecttestapp.startPage.network.ApiService
import com.example.projecttestapp.startPage.network.ServiceBuilder
import retrofit2.Call
import retrofit2.Response

class TeacherModel(var sharedPreferences: SharedPreferences):TeacherContract.Model {
    private val userService = ServiceBuilder.builService(ApiService::class.java)
    override fun getFromSharedPreferences(shared_preferences_editor: SharedPreferences.Editor) {

    }

    override fun putSharedPreferences(teacher:Teacher) {
        val shared_preferences_editor:SharedPreferences.Editor = sharedPreferences.edit()
       /* if(!sharedPreferences.contains(Constants.APP_PREFERENCES_KEY)){
            shared_preferences_editor.putInt(Constants.APP_PREFERENCES_KEY,teacher.id)
            shared_preferences_editor.putString(Constants.APP_PREFERENCES_LOGIN,teacher.login)
            shared_preferences_editor.putString(Constants.APP_PREFERENCES_PASSWORD,teacher.password)
            shared_preferences_editor.apply()
        }else{
            if(!teacher.id.equals(sharedPreferences.getInt(Constants.APP_PREFERENCES_KEY,0))){
                shared_preferences_editor.putInt(Constants.APP_PREFERENCES_KEY,teacher.id)
                shared_preferences_editor.putString(Constants.APP_PREFERENCES_LOGIN,teacher.login)
                shared_preferences_editor.putString(Constants.APP_PREFERENCES_PASSWORD,teacher.password)
                shared_preferences_editor.apply()
            }
        }*/
        shared_preferences_editor.putInt(Constants.APP_PREFERENCES_KEY,teacher.id)
        shared_preferences_editor.putString(Constants.APP_PREFERENCES_LOGIN,teacher.login)
        shared_preferences_editor.putString(Constants.APP_PREFERENCES_PASSWORD,teacher.password)
        shared_preferences_editor.apply()
    }

    override fun getTeacherByLogin(onFinishedListener: TeacherContract.Model.OnFinishedListener, login:String, password:String) {
        val requestCall = userService.getTeacherByLogin(login,null)
        requestCall.enqueue(object : retrofit2.Callback<ArrayList<Teacher>> {
            override fun onFailure(call: Call<ArrayList<Teacher>>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }
            override fun onResponse(call: Call<ArrayList<Teacher>>, response: Response<ArrayList<Teacher>>) {
                onFinishedListener.onFinishedTeacher(response.body()!!,response.isSuccessful)
            }
        })
    }
}