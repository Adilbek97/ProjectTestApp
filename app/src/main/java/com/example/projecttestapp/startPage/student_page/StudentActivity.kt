package com.example.projecttestapp.startPage.student_page

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.projecttestapp.R
import com.example.projecttestapp.startPage.Constants
import com.example.projecttestapp.startPage.authentication_page.AuthenticationContract
import com.example.projecttestapp.startPage.models.Result
import com.example.projecttestapp.startPage.models.Student
import com.example.projecttestapp.startPage.models.Subject
import com.example.projecttestapp.startPage.student_page.see_students_result_page.Student_SeeResultActivity
import com.example.projecttestapp.startPage.teacher_page.select_subject_page.SelectSubjectActivity
import kotlinx.android.synthetic.main.activity_student.*
import retrofit2.Call
import retrofit2.Response

class StudentActivity : AppCompatActivity() {
    var sharedPreferences: SharedPreferences? = null
    lateinit var student: Student
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)
        //------ setting Shared Preferences
        sharedPreferences  = getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
        //-----------------------
        val bundle:Bundle? = intent.extras
        if(bundle == null){
            getStudentByLogin(sharedPreferences!!.getString(Constants.APP_PREFERENCES_LOGIN,""),sharedPreferences!!.getString(Constants.APP_PREFERENCES_PASSWORD,""))
        }else{
            student= bundle.getParcelable("student_key") as Student
            putSharedPreferences(student)
        }
//        student= bundle.getParcelable("student_key") as Student
//        putSharedPreferences(student)
        student_see_results_btn.setOnClickListener {
            var intent1 = Intent(this, Student_SeeResultActivity::class.java)
            var res:ArrayList<Result> = student.results as ArrayList<Result>
            intent1.putExtra("results_key",res)
            startActivity(intent1)
        }
        pass_test_btn.setOnClickListener {
            var intent = Intent(this, SelectSubjectActivity::class.java)
            getSubjects(intent)
        }
    }

    fun putSharedPreferences(student: Student){
        Log.i("StudentActivity","puting to shared preferences method")
        val shared_preferences_editor:SharedPreferences.Editor = sharedPreferences!!.edit()
      /*  if(!sharedPreferences!!.contains(Constants.APP_PREFERENCES_KEY)){
            Log.i("StudentActivity","puting to shared preferences first if")
            shared_preferences_editor.putInt(Constants.APP_PREFERENCES_KEY,student.id)
            shared_preferences_editor.putString(Constants.APP_PREFERENCES_LOGIN,student.login)
            shared_preferences_editor.putString(Constants.APP_PREFERENCES_PASSWORD,student.password)
            shared_preferences_editor.apply()
        }else{
            if(!student.id.equals(sharedPreferences!!.getInt(Constants.APP_PREFERENCES_KEY,0)) && !student.login.equals(sharedPreferences!!.getString(Constants.APP_PREFERENCES_LOGIN,""))){
                Log.i("StudentActivity","puting to shared preferences second if")
                shared_preferences_editor.putInt(Constants.APP_PREFERENCES_KEY,student.id)
                shared_preferences_editor.putString(Constants.APP_PREFERENCES_LOGIN,student.login)
                shared_preferences_editor.putString(Constants.APP_PREFERENCES_PASSWORD,student.password)
                shared_preferences_editor.apply()
            }
        }*/
        shared_preferences_editor.putInt(Constants.APP_PREFERENCES_KEY,student.id)
        shared_preferences_editor.putString(Constants.APP_PREFERENCES_LOGIN,student.login)
        shared_preferences_editor.putString(Constants.APP_PREFERENCES_PASSWORD,student.password)
        shared_preferences_editor.apply()
    }

    fun getSubjects(intent: Intent) {
        val requestCall = Constants.userService.getSubjects()
        requestCall.enqueue(object : retrofit2.Callback<ArrayList<Subject>> {
            override fun onFailure(call: Call<ArrayList<Subject>>, t: Throwable) {
            }
            override fun onResponse(call: Call<ArrayList<Subject>>, response: Response<ArrayList<Subject>>) {
                intent.putExtra("activity_id",5)
                intent.putExtra("subject_key",response.body())
                startActivity(intent)
            }
        })
    }

    fun getStudentByLogin(login:String, password:String) {
        val requestCall = Constants.userService.getStudentByLogin(login, null)
        requestCall.enqueue(object : retrofit2.Callback<ArrayList<Student>> {
            override fun onFailure(call: Call<ArrayList<Student>>, t: Throwable) {

//                showToast(getString(R.string.authenticate_activity_problem_with_internet_text))
            }
            override fun onResponse(call: Call<ArrayList<Student>>, response: Response<ArrayList<Student>>) {
                student= response.body()!!.get(0)
                putSharedPreferences(student)
            }
        })
    }
}
