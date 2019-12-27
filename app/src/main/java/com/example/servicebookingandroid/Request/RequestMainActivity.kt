package com.example.servicebookingandroid.Request

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.example.servicebookingandroid.Auth.AuthBaseActivity
import com.example.servicebookingandroid.BaseActivity
import com.example.servicebookingandroid.R
import kotlinx.android.synthetic.main.activity_request_main.*

class RequestMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_main)
    }

    fun initView() {
        if (AuthBaseActivity.user.role == "Customer") {
            sp_language.setVisibility(View.INVISIBLE)
            sp_serviceType.setVisibility(View.INVISIBLE)
            tv_language.setVisibility(View.INVISIBLE)
            tv_serviceType.setVisibility(View.INVISIBLE)
        }

        if (AuthBaseActivity.user.role == "Service") {
            bt_postRequest.setVisibility(View.INVISIBLE)
            setServiceTypeSpiner()
            setLanguageSpiner()
        }
    }

    fun onRequests(view: View) {
        if (AuthBaseActivity.user.role == "Service") {
            selectedServiceType = sp_serviceType.selectedItem.toString()
            selectedLanguage = sp_language.selectedItem.toString()
        }

        startActivity(Intent(this, RequestListActivity::class.java))
    }

    fun onPostRequest(view: View) {
        startActivity(Intent(this, RequestFormActivity::class.java))
    }

    fun setServiceTypeSpiner(){
        var ServiceTypeNames: ArrayList<String> = ArrayList<String>();
        ServiceTypeNames.add("All")
        ServiceTypeNames.addAll(BaseActivity.BaseserviceTypes)
        var arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(baseContext, android.R.layout.simple_spinner_item, ServiceTypeNames)
        sp_serviceType.setAdapter(arrayAdapter)
    }

    fun setLanguageSpiner() {
        val languageNames = ArrayList<String>()
        languageNames.add("All")
        languageNames.addAll(BaseActivity.Baselanguages)
        val arrayAdapter = ArrayAdapter(baseContext, android.R.layout.simple_spinner_item, languageNames)
        sp_language.adapter = arrayAdapter
    }

    override fun onStart() {
        super.onStart()
        initView()
    }
}
