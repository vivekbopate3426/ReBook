package com.firstapp.vsbapk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Signup_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final EditText name_edit = findViewById(R.id.Name);
        final EditText email_edittext = findViewById(R.id.Email);
        final  EditText password_edittext = findViewById(R.id.password);
        final TextView signup_button = findViewById(R.id.signup_button);
    }
}