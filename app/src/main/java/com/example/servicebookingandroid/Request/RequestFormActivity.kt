package com.example.servicebookingandroid.Request

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.servicebookingandroid.Auth.AuthBaseActivity
import com.example.servicebookingandroid.Model.APIRequests.RequestOrderRequest
import com.example.servicebookingandroid.Model.RequestDto
import com.example.servicebookingandroid.R
import kotlinx.android.synthetic.main.activity_request_form.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RequestFormActivity : RequestBaseActivity2() {

    var index: Int = -1
    var editMode: Boolean = false
    lateinit var ftView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_form)
        initView()
        initValue()
    }

    fun initView() {
        var li: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        ftView = li.inflate(R.layout.foot_view, null)
    }

    fun initValue() {
        var intent: Intent = getIntent()
        var i: Int = intent.getIntExtra("index", -1)
        if (i == -1) {
            setServiceTypeSpiner()
            return
        }
        index = i
        editMode = true
        tv_title.text = "Update Request"
        ed_text.setText(requestDtoList[index].info)
        setServiceTypeSpiner()
    }

    fun setServiceTypeSpiner() {
        var ServiceTypeNames = ArrayList<String>()
        ServiceTypeNames.add("")
        ServiceTypeNames.addAll(BaseserviceTypes)
        val arrayAdapter = ArrayAdapter<String>(baseContext, android.R.layout.simple_spinner_item, ServiceTypeNames)
        sp_serviceType.adapter = arrayAdapter
        if (editMode) {
            val selectedPosition = arrayAdapter.getPosition(requestDtoList[index].servicetype)
            sp_serviceType.setSelection(selectedPosition)
        }
    }

    fun onSubmit(view: View){
        val serviceType = sp_serviceType.selectedItem.toString()
        val info = ed_text.text.toString()

        if (TextUtils.isEmpty(serviceType)) {
            Toast.makeText(applicationContext, "Select serviceType", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(info)) {
            Toast.makeText(applicationContext, "Enter The Request Information", Toast.LENGTH_SHORT).show()
            return
        }

        linearLayout.addView(ftView)
        val requestOrderRequest = RequestOrderRequest(serviceType, info)
        val token = AuthBaseActivity.token

        var call = requestService.createRequest(token, requestOrderRequest)

        if (editMode) {
            val id = requestDtoList[index].requestId.toString()
            call = requestService.updateRequest(id, token, requestOrderRequest)
        }

        call.enqueue(object : Callback<RequestDto> {
            override fun onResponse(call: Call<RequestDto>, response: Response<RequestDto>) {
                linearLayout.removeView(ftView)
                if (!response.isSuccessful) {
                    Toast.makeText(applicationContext, "Server Error", Toast.LENGTH_SHORT).show()
                    return
                }

                if (editMode) {
                    requestDtoList[index] = response.body()!!
                } else {
                    requestDtoList.add(response.body()!!)
                }
                finish()
            }

            override fun onFailure(call: Call<RequestDto>, t: Throwable) {}
        })
    }

    override fun onStart() {
        super.onStart()
    }
}
