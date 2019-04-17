package com.example.projecttestapp.startPage.teacher_page

import android.content.SharedPreferences
import com.example.projecttestapp.startPage.Constants
import com.example.projecttestapp.startPage.models.Teacher

class TeacherModel(var sharedPreferences: SharedPreferences):TeacherContract.Model {

    override fun getFromSharedPreferences(shared_preferences_editor: SharedPreferences.Editor) {
    }

    override fun putSharedPreferences(teacher:Teacher) {
        val shared_preferences_editor:SharedPreferences.Editor = sharedPreferences!!.edit()
        if(!sharedPreferences!!.contains(Constants.APP_PREFERENCES_KEY)){
            shared_preferences_editor.putInt(Constants.APP_PREFERENCES_KEY,teacher.id)
            shared_preferences_editor.putString(Constants.APP_PREFERENCES_LOGIN,teacher.login)
            shared_preferences_editor.putString(Constants.APP_PREFERENCES_PASSWORD,teacher.password)
            shared_preferences_editor.apply()
        }else{
            if(!teacher.id.equals(sharedPreferences!!.getInt(Constants.APP_PREFERENCES_KEY,0))){
                shared_preferences_editor.putInt(Constants.APP_PREFERENCES_KEY,teacher.id)
                shared_preferences_editor.putString(Constants.APP_PREFERENCES_LOGIN,teacher.login)
                shared_preferences_editor.putString(Constants.APP_PREFERENCES_PASSWORD,teacher.password)
                shared_preferences_editor.apply()
            }
        }
    }
}