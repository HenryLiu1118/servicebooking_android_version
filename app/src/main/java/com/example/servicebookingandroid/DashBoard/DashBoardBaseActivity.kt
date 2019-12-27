package com.example.servicebookingandroid.DashBoard

import com.example.servicebookingandroid.BaseActivity

open class DashBoardBaseActivity: BaseActivity() {
    override fun onStart() {
        super.onStart()
        CheckUserToken()
        checkDataSetted()
    }
}