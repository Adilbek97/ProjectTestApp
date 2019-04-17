package com.example.projecttestapp.startPage.teacher_page.select_subject_page

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.projecttestapp.startPage.adapters.SelectSubjectAdapter
import com.example.projecttestapp.startPage.models.Subject
import kotlinx.android.synthetic.main.activity_select_subject.*

class SelectSubjectPresenter(val view:AppCompatActivity):SelectSubjectContract.Presenter {
    val bundle = view.intent.extras!!
    val model = SelectSubjectModel()
    override fun settingRecyclerView() {
        val layoutManager = LinearLayoutManager(view)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        view.recyclerSelectSubject.layoutManager = layoutManager
        val subjects: ArrayList<Subject>? = model.getSubjects(bundle)
        if (subjects != null) {
            val adapter = SelectSubjectAdapter(view, subjects,model.getActivityId(bundle))
            view.recyclerSelectSubject.adapter = adapter
        }
    }
}