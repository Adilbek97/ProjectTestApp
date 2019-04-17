package com.example.projecttestapp.startPage.student_page.see_students_result_page

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.projecttestapp.R
import com.example.projecttestapp.startPage.adapters.SeeResultAdapter
import com.example.projecttestapp.startPage.models.Result
import kotlinx.android.synthetic.main.activity_student__see_result.*

class Student_SeeResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student__see_result)
        val bundle:Bundle = intent.extras!!
        val results:ArrayList<Result>? = bundle.getParcelableArrayList<Result>("results_key")
        val adapter:SeeResultAdapter? = SeeResultAdapter(this,results!!,null)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        student_acticity_see_result_recyclerView.layoutManager = layoutManager
        student_acticity_see_result_recyclerView.adapter = adapter
    }
}
