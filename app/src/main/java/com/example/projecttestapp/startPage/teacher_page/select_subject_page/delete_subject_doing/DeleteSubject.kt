package com.example.projecttestapp.startPage.teacher_page.select_subject_page.delete_subject_doing

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import android.util.Log
import com.example.projecttestapp.startPage.Constants
import com.example.projecttestapp.startPage.adapters.SelectSubjectAdapter
import com.example.projecttestapp.startPage.models.Teacher
import com.example.projecttestapp.startPage.network.ApiService
import com.example.projecttestapp.startPage.network.ServiceBuilder
import com.example.projecttestapp.startPage.showToast
import com.example.projecttestapp.startPage.teacher_page.TeacherActivity
import retrofit2.Call
import retrofit2.Response

class DeleteSubject(var adapter: SelectSubjectAdapter, val context: Context, val Subject_id: Int): AsyncTask<Void, Void, Void>() {
    private val userService = ServiceBuilder.builService(ApiService::class.java)
    var sharedPreferences: SharedPreferences? = null
    override fun doInBackground(vararg p0: Void?): Void? {
        Log.i("DeleteSubjectClass","doInBackground method is executing")
        deleteSubject(Subject_id)
        return  null
    }
    override fun onPostExecute(result: Void?) {
        Log.i("DeleteSubjectClass","onPostExecute method is executing")
        context.showToast("subject succesfully deleted")
        updateTeacher(sharedPreferences!!.getString(Constants.APP_PREFERENCES_LOGIN,null),"")

    }
    fun deleteSubject(id:Int){
        sharedPreferences  = context.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
        Log.i("DeleteSubjectClass","deleteSubject method is executing")
        val requestCall = userService.deleteSubject(id)
        requestCall.enqueue(object : retrofit2.Callback<Unit>{
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.i("DeleteSubjectClass","onFailure method is executing")
                context.showToast("Failed in deleting")
            }
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                Log.i("DeleteSubjectClass","onResponse method is executing")
                if(response.isSuccessful){
                    adapter.notifyDataSetChanged()
                }else{

                }
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
                var intent: Intent = Intent(context, TeacherActivity::class.java)
                intent.putExtra("teacher_key", teacher!!.get(0))
                context.showToast("subject succesfully deleted")
                context.startActivity(intent)

            }
        })
    }

}