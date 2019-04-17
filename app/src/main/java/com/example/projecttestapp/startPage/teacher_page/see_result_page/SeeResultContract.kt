package com.example.projecttestapp.startPage.teacher_page.see_result_page

import android.os.Bundle
import com.example.projecttestapp.startPage.models.Result
import com.example.projecttestapp.startPage.models.Student
import com.example.projecttestapp.startPage.models.Subject
import com.example.projecttestapp.startPage.models.Teacher

interface SeeResultContract {
    interface Model{

        interface OnFinishedListener {
            fun onFinishedStudent(student: ArrayList<Student>,isSucces:Boolean)
            fun onFailure(t: Throwable)
        }
        fun getSubject(bundle: Bundle):Subject
        fun getStudents(onFinishedListener: OnFinishedListener, bundle: Bundle)
    }
    interface View{
        fun onFailureMessage()
    }
    interface Presenter{
        fun settingRecyclerView(results:ArrayList<Student>)
    }
}