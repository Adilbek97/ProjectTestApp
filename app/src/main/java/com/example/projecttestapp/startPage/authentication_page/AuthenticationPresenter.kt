package com.example.projecttestapp.startPage.authentication_page

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.projecttestapp.R
import com.example.projecttestapp.startPage.Constants
import com.example.projecttestapp.startPage.models.Student
import com.example.projecttestapp.startPage.models.Teacher
import com.example.projecttestapp.startPage.register_page.RegisterActivity
import com.example.projecttestapp.startPage.showToast
import com.example.projecttestapp.startPage.student_page.StudentActivity
import com.example.projecttestapp.startPage.teacher_page.TeacherActivity
import kotlinx.android.synthetic.main.activity_authentication.*

class AuthenticationPresenter(var mContext: AppCompatActivity) :AuthenticationContract.Presenter,AuthenticationContract.Model.OnFinishedListener {
    var intent: Intent?=null
    var model: AuthenticationModel
    var sharedPreferences: SharedPreferences

    init {
        model = AuthenticationModel()
        sharedPreferences  = mContext.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
    }
    override fun onFinishedTeacher(teachers: ArrayList<Teacher>,isSucces:Boolean) {
         if (isSucces && teachers.size>0) {
             val teacher = teachers[0]
               if (mContext.authenticate_password_edit_text.text.toString().equals(teacher.password)) {
                   intent!!.putExtra("teacher_key", teacher)
                   mContext.startActivity(intent)
                   mContext.showToast(mContext.getString(R.string.authenticate_activity_wellcome_teacher_text) + teacher.firstName)
               } else {
                   mContext.showToast(mContext.getString(R.string.authenticate_activity_password_is_not_correct_text))
               }
           }else{
               this.mContext.showToast(mContext.getString(R.string.authenticate_activity_no_login_like_this_text))
           }
    }

    override fun onFinishedStudent(students: ArrayList<Student>,isSucces:Boolean) {
        if (isSucces && students.size > 0) {
            val student = students[0]
            if (mContext.authenticate_password_edit_text.text.toString().equals(student.password)) {
                intent!!.putExtra("student_key", student)
                mContext.startActivity(intent)
                mContext.showToast(mContext.getString(R.string.authenticate_activity_wellcome_student_text) + student.firstName)
            } else {
                mContext.showToast(mContext.getString(R.string.authenticate_activity_password_is_not_correct_text))
            }
        }else{
            mContext.showToast(mContext.getString(R.string.authenticate_activity_no_login_like_this_text))
        }
    }

    override fun onFailure(t: Throwable) {
        mContext.showToast(mContext.getString(R.string.authenticate_activity_problem_with_internet_text))
    }

    override fun checkStatus():String {
        val bundle: Bundle? = mContext.intent!!.extras
        val status = bundle!!.getString("status")
        if (status.equals("teacher")) {
            intent = Intent(mContext, TeacherActivity::class.java)
        }else{
            intent = Intent(mContext, StudentActivity::class.java)
        }
        return status
    }

    override fun login() {
//        model.getFromSharedPreferences()
        if (checkStatus().equals("teacher")){
                model.getTeacherByLogin(this,mContext.authenticate_login_edit_text.text.toString(),mContext.authenticate_password_edit_text.text.toString())
            }else{
                model.getStudentByLogin(this,mContext.authenticate_login_edit_text.text.toString(),mContext.authenticate_password_edit_text.text.toString())
            }
    }
    fun goToRegister(){
        intent = Intent(mContext,RegisterActivity::class.java)
        val status = mContext.intent!!.extras.getString("status")
        intent!!.putExtra("current_status",status)
        mContext.startActivity(intent)
    }
    fun getFromSharedPreferences(){
        sharedPreferences  = mContext.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
        if(sharedPreferences!!.contains(Constants.APP_PREFERENCES_KEY)){
            mContext.authenticate_login_edit_text.setText(sharedPreferences!!.getString(Constants.APP_PREFERENCES_LOGIN,""))
            mContext.authenticate_password_edit_text.setText(sharedPreferences!!.getString(Constants.APP_PREFERENCES_PASSWORD,""))
        }
    }
}