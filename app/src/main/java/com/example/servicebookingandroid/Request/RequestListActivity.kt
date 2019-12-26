package com.example.servicebookingandroid.Request

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.servicebookingandroid.Auth.AuthBaseActivity
import com.example.servicebookingandroid.Model.Response.RequestsResponse
import com.example.servicebookingandroid.R
import kotlinx.android.synthetic.main.activity_request_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestListActivity : AppCompatActivity() {

    lateinit var requestsAdapter: RequestsAdapter
    var page: Int = 0
    val limit = 3
    var size: Int = 0
    lateinit var ftView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_list)
    }

    fun initView() {
        requestsAdapter = RequestsAdapter(this, R.layout.list_view_request_item, RequestBaseActivity.requestDtoList)
        listView.setAdapter(requestsAdapter)
        val li = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        ftView = li.inflate(R.layout.foot_view, null)

        page = 0
        size = 0
        callAPI()
    }

    fun onPrev(view: View) {
        if (page <= 0) return
        page--
        callAPI()
        setButton()
    }

    fun onNext() {
        if (page >= Math.ceil(size.toDouble() / limit).toInt() - 1) return
        page++
        callAPI()
        setButton()
    }

    fun setButton() {
        bt_prev.isClickable = page > 0
        bt_next.isClickable = page < Math.ceil(size.toDouble() / limit).toInt() - 1
    }

    fun callAPI() {
        listView.addFooterView(ftView)

        val role = AuthBaseActivity.user.role
        val token = AuthBaseActivity.token

        var call = RequestBaseActivity.requestService.getMyRequests(page, limit, token)

        if (role == "Customer") {
            //call = requestService.getMyRequests(page, limit,token);
        } else if (role == "Service") {
            if (RequestBaseActivity.selectedLanguage != "All" && RequestBaseActivity.selectedServiceType != "All") {
                call = RequestBaseActivity.requestService.getRequestByServiceTypeAndLanguage(RequestBaseActivity.selectedServiceType, RequestBaseActivity.selectedLanguage, page, limit, token)
            } else if (RequestBaseActivity.selectedServiceType != "All") {
                call = RequestBaseActivity.requestService.getRequestsByServiceType(RequestBaseActivity.selectedServiceType, page, limit, token)
            } else if (RequestBaseActivity.selectedLanguage != "All") {
                call = RequestBaseActivity.requestService.getRequestByLanguage(RequestBaseActivity.selectedLanguage, page, limit, token)
            } else {
                call = RequestBaseActivity.requestService.getAllRequests(page, limit, token)
            }
        }

        call.enqueue(object : Callback<RequestsResponse> {
            override fun onResponse(call: Call<RequestsResponse>, response: Response<RequestsResponse>) {
                if (!response.isSuccessful) {
                    Toast.makeText(applicationContext, "Server Error", Toast.LENGTH_SHORT).show()
                    return
                }

                RequestBaseActivity.requestDtoList.clear()
                RequestBaseActivity.requestDtoList.addAll(response.body()!!.requestDtoList)
                requestsAdapter.notifyDataSetChanged()
                size = response.body()!!.size
                setButton()
                tv_listView.text = "List of Requests: $size total"

                listView.removeFooterView(ftView)
            }

            override fun onFailure(call: Call<RequestsResponse>, t: Throwable) {

            }
        })
    }

    override fun onStart() {
        super.onStart()
        initView()
    }
}
