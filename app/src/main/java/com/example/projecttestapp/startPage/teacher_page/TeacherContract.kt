package com.example.projecttestapp.startPage.teacher_page

import android.content.SharedPreferences
import com.example.projecttestapp.startPage.models.Teacher

interface TeacherContract {
    interface Model{

        interface OnFinishedListener {
            fun onFinishedTeacher(teachers: ArrayList<Teacher>,isSucces:Boolean)
            fun onFailure(t: Throwable)
        }

        fun getFromSharedPreferences(shared_preferences_editor: SharedPreferences.Editor)
        fun putSharedPreferences(teacher: Teacher)
        fun getTeacherByLogin(onFinishedListener: TeacherContract.Model.OnFinishedListener, login:String, password:String)
    }

    interface Presenter{
        fun parseTeacherFromIntent()
        fun goToSeeResults()
        fun goToAddQuestions()
    }
}