package com.example.projecttestapp.startPage.teacher_page.select_subject_page

import android.os.Bundle
import com.example.projecttestapp.startPage.models.Subject

interface SelectSubjectContract {
    interface Model {
        fun getSubjects(bundle: Bundle):ArrayList<Subject>?
        fun getActivityId(bundle: Bundle):Int
    }
    interface View{
        fun setDataToRecyclerView()
    }
    interface Presenter{
        fun settingRecyclerView()
    }
}