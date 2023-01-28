package com.firstapp.vsbapk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        final TextView name = findViewById(R.id.Welcome_name);
        name.setText("Welcome Back-" + user.getDisplayName());
        if (user != null) {
        } else {
            Intent intent = new Intent(MainActivity.this, Login_Activity.class);
            startActivity(intent);

        }
    }

}