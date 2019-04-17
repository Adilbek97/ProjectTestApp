package com.example.projecttestapp.startPage

import android.content.Context
import android.content.Intent
import com.example.projecttestapp.startPage.authentication_page.AuthenticationActivity

class StartPagePresenter : StartPageContract.StartPagePresenter {


    lateinit var intent:Intent
    var mContext:Context
    constructor(mContext: Context) {
        this.mContext = mContext
        intent = Intent(mContext, AuthenticationActivity::class.java)
    }
    override fun goToOtherActivity() {
        mContext.startActivity(intent)
    }
    override fun setStatus(status: String?) {
        intent.putExtra("status",status)
    }

}