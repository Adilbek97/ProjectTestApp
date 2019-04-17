package com.example.projecttestapp.startPage

import android.content.Context

interface StartPageContract {
    interface StartPagePresenter{
        fun goToOtherActivity()
        fun setStatus(status:String?)
    }
    interface StartPageView{

    }
    interface StartPageModel{

    }
}