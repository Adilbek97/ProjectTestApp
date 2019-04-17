package com.example.projecttestapp.startPage.register_page

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.projecttestapp.R
import com.example.projecttestapp.startPage.Constants
import com.example.projecttestapp.startPage.authentication_page.AuthenticationActivity
import com.example.projecttestapp.startPage.models.Student
import com.example.projecttestapp.startPage.models.Teacher
import com.example.projecttestapp.startPage.showToast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterPresenter(context: AppCompatActivity) :RegisterContract.Presenter,RegisterContract.Model.OnFinishedListener {


    var mContext:AppCompatActivity = context
    var mIntent:Intent?=null
    lateinit var status:String
    var sharedPreferences: SharedPreferences = mContext.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
    val model:RegisterModel = RegisterModel(sharedPreferences)
    override fun onFinishedTeacher(teacher: Teacher) {
        goToAuthentication()
    }

    override fun onFinishedStudent(student: Student) {
        goToAuthentication()
    }

    override fun onFailure(t: Throwable) {
        mContext.show_errors_tv.text = mContext.getString(R.string.register_activity_cant_add_text)
    }

    override fun checkErrors( firstname: String?,
        lastname: String?,
        login: String?,
        password: String?,
        password_again: String?
    ): String {
        if (firstname.equals(null) || firstname.equals("")) {
            return mContext.getString(R.string.reqister_activity_write_firstname_text)
        }
        if (lastname.equals(null) || lastname.equals("")) {
            return mContext.getString(R.string.register_activity_write_lastname_text)
        }
        if (login.equals(null) || login.equals("")) {
            return mContext.getString(R.string.register_activity_write_login_text)
        }
        if (password.equals(null) || password.equals("")) {
            return mContext.getString(R.string.register_activity_write_password_text)
        }
        if (password_again.equals(null) || password_again.equals("")) {
            return mContext.getString(R.string.register_activity_write_password_again_text)
        }
        if (!password.equals(password_again)) {
            return mContext.getString(R.string.register_activity_passwords_not_equal_text)
        }
        return "correct"
    }
    override fun register() {
        val firstname: String? = mContext.first_name_edit_text.text.toString()
        val lastname: String? = mContext.last_name_edit_text.text.toString()
        val login: String? = mContext.login_edit_text.text.toString()
        val password: String? = mContext.password_edit_text.text.toString()
        val password_again: String? = mContext.password_again_edit_text.text.toString()
        val error = checkErrors(firstname, lastname, login, password, password_again)
        val bundle: Bundle? = mContext.intent!!.extras
        status = bundle!!.getString("current_status")
        if (error.equals("correct")) {
            if (status.equals("teacher")){
                var teacher= Teacher()
                teacher.firstName=firstname
                teacher.lastName=lastname
                teacher.login=login
                teacher.password=password
                model.addTeacher(this,teacher)
            }else{
                val student = Student()
                student.firstName=firstname
                student.lastName=lastname
                student.login=login
                student.password=password
                model.addStudent(this,student)
            }
        } else {
            mContext.show_errors_tv.text = error
        }
    }
    fun goToAuthentication(){
        mIntent = Intent(mContext,AuthenticationActivity::class.java)
        mIntent!!.putExtra("status",status)
        mContext.startActivity(mIntent)
        mContext.show_errors_tv.text = mContext.getString(R.string.register_activity_succesfully_added_text)
        mContext.showToast(mContext.getString(R.string.register_activity_succesfully_added_text))
    }
}