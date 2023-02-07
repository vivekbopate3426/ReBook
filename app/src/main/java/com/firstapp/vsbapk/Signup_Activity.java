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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Signup_Activity extends AppCompatActivity {
private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final EditText name_edittext = findViewById(R.id.Name);
        final EditText email_edittext = findViewById(R.id.Email);
        final  EditText password_edittext = findViewById(R.id.password);
        final TextView signup_button = findViewById(R.id.signup_button);
        mAuth =FirebaseAuth.getInstance();
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_edittext.setError(null);
                email_edittext.setError(null);
                password_edittext.setError(null);
             String name= name_edittext.getText().toString();
             String email= email_edittext.getText().toString();
             String password= password_edittext.getText().toString();
             String signup= signup_button.getText().toString();
             if (TextUtils.isEmpty(name))
             {
                 name_edittext.setError("Please Enter Name");
                 return;
             }

                if (TextUtils.isEmpty(email))
                {
                    email_edittext.setError("Please Enter Email");
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    password_edittext.setError("Please Enter Password");
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    UserProfileChangeRequest
                                            profileUpdates= new
                                            UserProfileChangeRequest.Builder()
                                            .setDisplayName(name)
                                                    .build();
                                    user.updateProfile(profileUpdates)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()){
                                                             Toast.makeText(Signup_Activity.this,name,
                                                                        Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });
                                    Toast.makeText(getApplicationContext(),
                                           "SignUp Successful", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getApplicationContext(),
                                           "SignUp UnSuccessful", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        final TextView gotoSignUp= findViewById(R.id.sign_in);
        gotoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup_Activity.this,Login_Activity.class);
                startActivity(intent);

            }
        });



    }
}