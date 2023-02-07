package com.firstapp.vsbapk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        final ProgressBar progressBar= findViewById(R.id.welcome_progressbar);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        final TextView name = findViewById(R.id.Welcome_name);
        if (user!= null ) {
            name.setText("Welcome Back-" + user.getDisplayName());
            progressBar.setVisibility(View.GONE);
            Intent intent = new Intent(MainActivity.this, Home_Activity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(MainActivity.this, Login_Activity.class);
            progressBar.setVisibility(View.GONE);
            startActivity(intent);


           // NavController navController= Navigation.findNavController(this,R.id.fragmentContainerView);findViewById(R.id.fragmentContainerView);
        }

    }



}