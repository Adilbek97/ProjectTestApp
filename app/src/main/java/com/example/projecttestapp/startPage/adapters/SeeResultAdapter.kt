package com.example.projecttestapp.startPage.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projecttestapp.R
import com.example.projecttestapp.startPage.models.Student
import com.example.projecttestapp.startPage.models.Subject
import com.example.projecttestapp.startPage.models.Result
import com.example.projecttestapp.startPage.network.ApiService
import com.example.projecttestapp.startPage.network.ServiceBuilder
import kotlinx.android.synthetic.main.see_result_item.view.*
import retrofit2.Call
import retrofit2.Response

class SeeResultAdapter(val context: Context, var result: ArrayList<Result>, var students: ArrayList<Student>?) :
    RecyclerView.Adapter<SeeResultAdapter.MyViewHolder>() {
    private val userService = ServiceBuilder.builService(ApiService::class.java)
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.see_result_item, viewGroup, false)
        return MyViewHolder(view)
    }
    override fun getItemCount(): Int {
        return result.size
    }
    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {
        val res: Result = result[position]
        myViewHolder.setData(res, position)
    }
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var curResult: Result? = null
        var curPositin: Int = 0
        @SuppressLint("SetTextI18n")
        fun setData(result: Result?, position: Int) {
            this.curResult = result
            this.curPositin = position
            if (students != null) {
                itemView.subject_tv.text = getStudent(curResult).lastName + " " +  getStudent(curResult).firstName
                itemView.mark_tv.text = Math.round(curResult!!.result).toString()
            } else {
                getSubject(curResult!!.subject_id)
                itemView.mark_tv.text = Math.round(curResult!!.result).toString()
            }
        }

        fun getStudent(result: Result?):Student{
            for (student in students!!){
                if(student.id.equals(result!!.student_id)){
                    return student
                }
            }
            return students!![0]
        }

        fun getSubject(id: Int) {
            val requestCall = userService.getSubject(id)
            requestCall.enqueue(object : retrofit2.Callback<Subject> {
                override fun onFailure(call: Call<Subject>, t: Throwable) {
                    itemView.subject_tv.text = "unknown subject"
                }
                override fun onResponse(call: Call<Subject>, response: Response<Subject>) {
                    itemView.subject_tv.text = response.body()!!.name
                }
            })
        }
    }
}
