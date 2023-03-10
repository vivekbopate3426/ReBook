package com.firstapp.vsbapk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        final EditText email_edittext = findViewById(R.id.email);
        final EditText password_edittext = findViewById(R.id.password_toggle);
        final TextView login_button = findViewById(R.id.login_button_login);
        mAuth = FirebaseAuth.getInstance();
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_edittext.setError(null);
                password_edittext.setError(null);
                String emailText = email_edittext.getText().toString();
                String passwordText = password_edittext.getText().toString();
                if (TextUtils.isEmpty(emailText)) {
                    email_edittext.setError("Please Enter Email");
                    return;
                }
                if (TextUtils.isEmpty(passwordText)) {
                    password_edittext.setError("Please Enter Password");
                    return;
                }
                mAuth.signInWithEmailAndPassword(emailText, passwordText)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                            "Login Successful", Toast.LENGTH_SHORT).show();
                                }else {
                                        Toast.makeText(getApplicationContext(),
                                                "Login UnSuccessful", Toast.LENGTH_SHORT).show();
                                }
                                }

                            });
            }
                        });
        final TextView gotoSignUp= findViewById(R.id.sign_up);
        gotoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activity.this,Signup_Activity.class);
                startActivity(intent);
            }
        });
        final TextView ForgotPassword=findViewById(R.id.forgot_password);
        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activity.this,Reset_Password_Activity.class);
                startActivity(intent);

            }
        });

            }

        }
