package com.firstapp.vsbapk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Reset_Password_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        final EditText email=findViewById(R.id.resetPass_email);
        final TextView sendemail=findViewById(R.id.resetPass_SendEmail);
        sendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email.setError(null);
               String emailText = email.getText().toString();
               if (TextUtils.isEmpty(emailText)) {
                   email.setError("Please Enter Email ");
                   return;
               }else{
                   FirebaseAuth mAuth= FirebaseAuth.getInstance();
                           mAuth.sendPasswordResetEmail(emailText)
                                   .addOnCompleteListener(new OnCompleteListener<Void>() {
                                       @Override
                                       public void onComplete(@NonNull Task<Void> task) {
                                           if (task.isSuccessful()) {
                                               Toast.makeText(Reset_Password_Activity.this, "Password reest email sent Successfully", Toast.LENGTH_SHORT).show();
                                           }else {
                                               Toast.makeText(Reset_Password_Activity.this, "Something went Wrong! Try Again!", Toast.LENGTH_SHORT).show();
                                           }
                                       }
                                   });

               }
            }
        });
    }
}