package com.example.servicebookingandroid.DashBoard;

import com.example.servicebookingandroid.BaseActivity;

public class DashBoardBaseActivity extends BaseActivity {

    @Override
    protected void onStart() {
        super.onStart();
        CheckUserToken();
        checkDataSetted();
    }
}
