package com.example.projecttestapp.startPage.teacher_page

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.projecttestapp.R
import com.example.projecttestapp.startPage.Constants
import com.example.projecttestapp.startPage.models.Teacher
import com.example.projecttestapp.startPage.showToast
import com.example.projecttestapp.startPage.teacher_page.select_subject_page.SelectSubjectActivity
import kotlinx.android.synthetic.main.activity_teacher.*
class TeacherPresenter(val mContext:AppCompatActivity):TeacherContract.Presenter,TeacherContract.Model.OnFinishedListener{

    lateinit var teacher: Teacher
    var sharedPreferences: SharedPreferences = mContext.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
    val model = TeacherModel(sharedPreferences)
    lateinit var intent:Intent
    var bundle:Bundle? = null
    init {
        intent = mContext.intent
        bundle = intent.extras
        if (bundle == null) {
            model.getTeacherByLogin(
                this, sharedPreferences.getString(Constants.APP_PREFERENCES_LOGIN, "none"),
                sharedPreferences.getString(Constants.APP_PREFERENCES_PASSWORD, "none")
            )
        }else{
            teacher = bundle!!.getParcelable("teacher_key") as Teacher
            model.putSharedPreferences(teacher)
            mContext.title_teacher_tv.text = "Wellcome " + teacher.firstName
            intent= Intent(mContext, SelectSubjectActivity::class.java)
            intent.putExtra("subject_key",teacher.subjects)
        }
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

    override fun onFinishedTeacher(teachers: ArrayList<Teacher>, isSucces: Boolean) {
        if (isSucces && teachers.size>0) {
            teacher = teachers[0]
            model.putSharedPreferences(teacher)
            mContext.title_teacher_tv.text = "Wellcome " + teacher.firstName
            intent= Intent(mContext, SelectSubjectActivity::class.java)
            intent.putExtra("subject_key",teacher.subjects)
        }
    }

    override fun onFailure(t: Throwable) {
        mContext.showToast(mContext.getString(R.string.authenticate_activity_problem_with_internet_text))
    }
}