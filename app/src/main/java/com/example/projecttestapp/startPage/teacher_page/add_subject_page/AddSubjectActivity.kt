package com.example.projecttestapp.startPage.teacher_page.add_subject_page

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.projecttestapp.R
import com.example.projecttestapp.startPage.Constants
import com.example.projecttestapp.startPage.models.Subject
import com.example.projecttestapp.startPage.models.Teacher
import com.example.projecttestapp.startPage.showToast
import com.example.projecttestapp.startPage.teacher_page.TeacherActivity
import kotlinx.android.synthetic.main.activity_add_subject.*
import retrofit2.Call
import retrofit2.Response

class AddSubjectActivity : AppCompatActivity() {
    var sharedPreferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_subject)
        //------ setting Shared Preferences
        sharedPreferences  = getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
        //-----------------------
        add_subject_activity_add_subject_btn.setOnClickListener {
            if (!add_subject_activity_name_of_subject_et.text.equals(null) || add_subject_activity_name_of_subject_et.text.toString() != ""){
                var subject:Subject = Subject()
                subject.name = add_subject_activity_name_of_subject_et.text.toString()
                subject.teacher = sharedPreferences!!.getInt(Constants.APP_PREFERENCES_KEY,0);
                addSubject(subject)
            }
        }
    }

    fun addSubject(subject: Subject){
        val requestCall = Constants.userService.addSubject(subject)
        requestCall.enqueue(object : retrofit2.Callback<Subject>{
            override fun onFailure(call: Call<Subject>, t: Throwable) {
                showToast(getString(R.string.authenticate_activity_problem_with_internet_text))
            }
            override fun onResponse(call: Call<Subject>, response: Response<Subject>) {
                updateTeacher(sharedPreferences!!.getString(Constants.APP_PREFERENCES_LOGIN,null),"")
            }
        })
    }
    fun updateTeacher(login:String,password:String?){
        var teacher:ArrayList<Teacher>?=null
        val requestCall = Constants.userService.getTeacherByLogin(login,password)
        requestCall.enqueue(object : retrofit2.Callback<ArrayList<Teacher>>{
            override fun onFailure(call: Call<ArrayList<Teacher>>, t: Throwable) {
                teacher=null
            }
            override fun onResponse(call: Call<ArrayList<Teacher>>, response: Response<ArrayList<Teacher>>) {
                teacher = response.body()
                var intent: Intent = Intent(this@AddSubjectActivity, TeacherActivity::class.java)
                intent.putExtra("teacher_key", teacher!!.get(0))
                startActivity(intent)
                showToast(getString(R.string.register_activity_succesfully_added_text))
            }
        })
    }
}
