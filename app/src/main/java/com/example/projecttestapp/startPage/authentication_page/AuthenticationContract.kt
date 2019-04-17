package com.example.projecttestapp.startPage.authentication_page

import com.example.projecttestapp.startPage.models.Student
import com.example.projecttestapp.startPage.models.Teacher

interface AuthenticationContract {
    interface Model{

        interface OnFinishedListener {
            fun onFinishedTeacher(teachers: ArrayList<Teacher>,isSucces:Boolean)
            fun onFinishedStudent(student: ArrayList<Student>,isSucces:Boolean)
            fun onFailure(t: Throwable)
        }
        fun getFromSharedPreferences()
        fun getStudentByLogin(onFinishedListener: OnFinishedListener,login:String,password:String)
        fun getTeacherByLogin(onFinishedListener: OnFinishedListener,login:String,password:String)
    }
    interface Presenter{
        fun checkStatus():String
        fun login()
    }
    interface View{

    }
}