package com.example.projecttestapp.startPage.teacher_page.select_subject_page

import android.os.Bundle
import com.example.projecttestapp.startPage.models.Subject

class SelectSubjectModel:SelectSubjectContract.Model {
    override fun getSubjects(bundle: Bundle): ArrayList<Subject>? {
        return bundle.getParcelableArrayList<Subject>("subject_key")
    }

    override fun getActivityId(bundle: Bundle): Int {
        return bundle.getInt("activity_id")
    }
}