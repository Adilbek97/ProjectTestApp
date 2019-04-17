package com.example.projecttestapp.startPage.teacher_page.see_result_page

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.projecttestapp.startPage.adapters.SeeResultAdapter
import com.example.projecttestapp.startPage.models.Result
import com.example.projecttestapp.startPage.models.Student
import com.example.projecttestapp.startPage.showToast
import kotlinx.android.synthetic.main.activity_see_result.*

class SeeResultPresenter(val view:AppCompatActivity):SeeResultContract.Presenter,SeeResultContract.Model.OnFinishedListener {
    val bundle: Bundle = view.intent.extras!!
    val model = SeeResultModel()
    
    override fun onFinishedStudent(student: ArrayList<Student>, isSucces: Boolean) {
        settingRecyclerView(student)
    }

    override fun onFailure(t: Throwable) {
        view.showToast("We cant get students . Problem with Internet or something doing uncorrect")
    }

    override fun settingRecyclerView(students:ArrayList<Student>) {
        val layoutManager = LinearLayoutManager(view)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        view.recyclerView.layoutManager = layoutManager
        val adapter: SeeResultAdapter? = SeeResultAdapter(view, model.getSubject(bundle).results!!,students)
        view.recyclerView.adapter = adapter
    }
    fun seeResult(){
        model.getStudents(this,bundle)
    }
}