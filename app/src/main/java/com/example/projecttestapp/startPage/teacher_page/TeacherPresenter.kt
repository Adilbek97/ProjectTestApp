package com.example.projecttestapp.startPage.teacher_page

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.projecttestapp.startPage.Constants
import com.example.projecttestapp.startPage.models.Teacher
import com.example.projecttestapp.startPage.teacher_page.select_subject_page.SelectSubjectActivity
import kotlinx.android.synthetic.main.activity_teacher.*
class TeacherPresenter(val mContext:AppCompatActivity):TeacherContract.Presenter{

    lateinit var teacher: Teacher
    var sharedPreferences: SharedPreferences = mContext.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
    val model = TeacherModel(sharedPreferences)
    var intent:Intent
    init {
        parseTeacherFromIntent()
        model.putSharedPreferences(teacher)
        mContext.title_teacher_tv.text = "Wellcome " + teacher.firstName
        intent= Intent(mContext, SelectSubjectActivity::class.java)
        intent.putExtra("subject_key",teacher.subjects)
    }
    override fun parseTeacherFromIntent() {
        val bundle: Bundle = mContext.intent.extras!!
        teacher = bundle.getParcelable("teacher_key") as Teacher
    }
    override fun goToSeeResults() {
        intent.putExtra("activity_id", 1)
        mContext.startActivity(intent)
    }

    override fun goToAddQuestions() {
        intent.putExtra("activity_id", 2)
        mContext.startActivity(intent)
    }
}