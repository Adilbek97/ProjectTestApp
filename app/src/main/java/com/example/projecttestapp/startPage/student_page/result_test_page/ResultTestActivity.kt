package com.example.projecttestapp.startPage.student_page.result_test_page

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.projecttestapp.R
import com.example.projecttestapp.startPage.Constants
import com.example.projecttestapp.startPage.models.Student
import com.example.projecttestapp.startPage.models.Result
import com.example.projecttestapp.startPage.showToast
import com.example.projecttestapp.startPage.student_page.StudentActivity
import kotlinx.android.synthetic.main.activity_result_test.*
import retrofit2.Call
import retrofit2.Response

class ResultTestActivity : AppCompatActivity() {
    var sharedPreferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_test)
        val bundle = intent.extras!!
        val result:Result = bundle.getParcelable("result_key") as Result
        sharedPreferences  = getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
        result_test_activity_result_tv.text = result.result.toString()
        addResult(result)
        result_test_activity_go_to_mainPage_btn.setOnClickListener {
            val intent:Intent = Intent(this, StudentActivity::class.java)
            uploadStudent(sharedPreferences!!.getInt(Constants.APP_PREFERENCES_KEY,1),intent)
        }
    }
    fun addResult(result: Result){
        val requestCall = Constants.userService.addResult(result)
        requestCall.enqueue(object : retrofit2.Callback<Result>{
            override fun onFailure(call: Call<Result>, t: Throwable) {
            }
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                showToast("succesfully added to database")
            }
        })
    }

    fun uploadStudent(id:Int,intent: Intent){
        lateinit var student: Student
        val requestCall = Constants.userService.getStudent(id)
        requestCall.enqueue(object : retrofit2.Callback<Student>{
            override fun onFailure(call: Call<Student>, t: Throwable) {
            }
            override fun onResponse(call: Call<Student>, response: Response<Student>) {
                student = response.body()!!
                intent.putExtra("student_key",student)
                startActivity(intent)

            }
        })

    }
}
