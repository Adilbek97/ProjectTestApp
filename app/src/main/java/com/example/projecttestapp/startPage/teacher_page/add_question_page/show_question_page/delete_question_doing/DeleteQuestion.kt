package com.example.projecttestapp.startPage.teacher_page.add_question_page.show_question_page.delete_question_doing

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import android.util.Log
import com.example.projecttestapp.startPage.Constants
import com.example.projecttestapp.startPage.models.Teacher
import com.example.projecttestapp.startPage.network.ApiService
import com.example.projecttestapp.startPage.network.ServiceBuilder
import com.example.projecttestapp.startPage.showToast
import com.example.projecttestapp.startPage.teacher_page.TeacherActivity
import retrofit2.Call
import retrofit2.Response

class DeleteQuestion(val context: Context, val Question_id: Int): AsyncTask<Void, Void, Void>() {
    private val userService = ServiceBuilder.builService(ApiService::class.java)
    var sharedPreferences: SharedPreferences? = null

    override fun onPostExecute(result: Void?) {
        Log.i("DeleteSubjectClass","onPostExecute method is executing")
        val intent: Intent = Intent(context, TeacherActivity::class.java)
        context.showToast("question succesfully deleted")
        context.startActivity(intent)

    }
    override fun doInBackground(vararg p0: Void?): Void? {
            Log.i("DeleteSubjectClass","doInBackground method is executing")
            deleteQuestion(Question_id)
        return null
    }

    fun deleteQuestion(id:Int){
        sharedPreferences  = context.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
        Log.i("DeleteQuestionClass","deleteQuestion method is executing")
        val requestCall = userService.deleteQuestion(id)
        requestCall.enqueue(object : retrofit2.Callback<Unit>{
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.i("DeleteQuestionClass","onFailure method is executing")
                context.showToast("Failed in deleting")
            }
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                Log.i("DeleteQuestionClass","onResponse method is executing")

            }
        })
    }

}