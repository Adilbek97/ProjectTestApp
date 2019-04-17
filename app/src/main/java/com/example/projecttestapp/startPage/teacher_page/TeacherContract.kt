package com.example.projecttestapp.startPage.teacher_page

import android.content.SharedPreferences
import com.example.projecttestapp.startPage.models.Teacher

interface TeacherContract {
    interface Model{
        fun getFromSharedPreferences(shared_preferences_editor: SharedPreferences.Editor)
        fun putSharedPreferences(teacher: Teacher)
    }
    interface Presenter{
        fun parseTeacherFromIntent()
        fun goToSeeResults()
        fun goToAddQuestions()
    }
}