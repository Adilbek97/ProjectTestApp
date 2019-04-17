package com.example.projecttestapp.startPage.register_page

import com.example.projecttestapp.startPage.models.Student
import com.example.projecttestapp.startPage.models.Teacher

interface RegisterContract {
    interface Model{
        interface OnFinishedListener {
            fun onFinishedTeacher(teacher: Teacher)
            fun onFinishedStudent(student: Student)
            fun onFailure(t: Throwable)
        }
        fun addTeacher(onFinishedListener: OnFinishedListener,teacher: Teacher)
        fun addStudent(onFinishedListener: OnFinishedListener,student: Student)
        fun putTeacherToSharedPreferences(teacher:Teacher)
        fun putStudentToSharedPreferences(student: Student)
    }
    interface View{

    }
    interface Presenter{
        fun checkErrors(firstname: String?,lastname: String?,login: String?,password: String?,password_again: String?): String
        fun register()
    }
}