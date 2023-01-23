package com.firstapp.vsbapk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Login_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
    }
}