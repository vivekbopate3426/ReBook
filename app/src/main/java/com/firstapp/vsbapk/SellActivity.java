package com.firstapp.vsbapk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstapp.vsbapk.fragment.sell_fragment;

public class SellActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sell);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.sell_placeholder,new sell_fragment())
                .commit();
    }
}