package com.example.servicebookingandroid.DashBoard

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.servicebookingandroid.Admin.LanguageListActivity
import com.example.servicebookingandroid.Admin.RoleListActivity
import com.example.servicebookingandroid.Admin.ServiceTypeActivity
import com.example.servicebookingandroid.Admin.UserListActivity
import com.example.servicebookingandroid.Auth.AuthBaseActivity
import com.example.servicebookingandroid.Model.UserDto
import com.example.servicebookingandroid.R
import com.example.servicebookingandroid.Request.RequestBaseActivity
import com.example.servicebookingandroid.Request.RequestMainActivity
import com.example.servicebookingandroid.ServiceProvide.ServiceBaseActivity
import com.example.servicebookingandroid.ServiceProvide.ServiceDetailActivity
import com.example.servicebookingandroid.ServiceProvide.ServiceMainActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DashBoardBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun initView() {
        if (AuthBaseActivity.user == null) return

        val user = AuthBaseActivity.user
        if (user.role == "Admin") {
            bt_requests.visibility = View.GONE
            bt_service.visibility = View.GONE
        } else {
            bt_languages.visibility = View.GONE
            bt_serviceType.setVisibility(View.GONE)
            bt_roles.setVisibility(View.GONE)
            bt_users.setVisibility(View.GONE)
            if (user.role == "Service") {
                bt_requests.setText("All Requests")
                bt_service.text = "My Service"
            } else {
                bt_requests.text = "My Requests"
                this.bt_service.setText("All Services")
            }
        }
        setProfile(user)
    }

    fun setProfile(user: UserDto) {
        tv_name.text = "Name: " + user.firstname + " " + user.lastname
        tv_email.text = "Email: " + user.username
        tv_phone.text = "Phone Number: " + user.phone
        tv_location.text = "Location: " + user.streetname + ", " + user.city + ", " + user.state + ", " + user.zipcode
    }

    fun onRequests(view: View) {
        startActivity(Intent(this@MainActivity, RequestMainActivity::class.java))
    }

    fun onService(view: View) {
        val role = AuthBaseActivity.user.role
        if (role == "Customer") {
            startActivity(Intent(this, ServiceMainActivity::class.java))
        } else if (role == "Service") {
            startActivity(Intent(this, ServiceDetailActivity::class.java))
        }
    }

    fun onRoles(view: View) {
        startActivity(Intent(this@MainActivity, RoleListActivity::class.java))
    }

    fun onLanguages(view: View) {
        startActivity(Intent(this@MainActivity, LanguageListActivity::class.java))
    }

    fun onServiceTypes(view: View) {
        startActivity(Intent(this@MainActivity, ServiceTypeActivity::class.java))
    }

    fun onUsers(view: View) {
        startActivity(Intent(this@MainActivity, UserListActivity::class.java))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.Logout -> logout()
            R.id.UpdateAccount -> EditProfile()
        }
        return super.onOptionsItemSelected(item)
    }

    fun EditProfile() {
        startActivity(Intent(this@MainActivity, EditProfileActivity::class.java))
    }

    fun logout() {
        val alert = AlertDialog.Builder(this@MainActivity)
        val negativeButton = alert.setMessage("Logout?")
                .setCancelable(false)
                .setPositiveButton("No", DialogInterface.OnClickListener { dialogInterface, i -> return@OnClickListener })
                .setNegativeButton("Yes") { dialogInterface, i ->
                    //authService
                    AuthBaseActivity.clearAuthData()
                    //RequestService
                    RequestBaseActivity.clearRequestData()
                    //ServiceProvideService
                    ServiceBaseActivity.clearServiceData()
                    //Base
                    clearBaseData()
                    CheckUserToken()
                }
        val A = alert.create()
        A.show()
    }

    override fun onStart() {
        super.onStart()
        initView()
    }
}
