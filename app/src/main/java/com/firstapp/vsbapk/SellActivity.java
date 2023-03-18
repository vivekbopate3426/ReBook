package com.firstapp.vsbapk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.firstapp.vsbapk.fragment.Book;
import com.firstapp.vsbapk.fragment.ProductDetails;
import com.firstapp.vsbapk.fragment.sell_fragment;

public class SellActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sell);
        Book book = (Book)getIntent().getSerializableExtra("Book") ;
        String caller = getIntent().getStringExtra("CALLER");
        if (caller.matches("sell")){

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.sell_placeholder, new sell_fragment() )
                    .commit();
            ProductDetails productDetails = new ProductDetails();

        }else {
            ProductDetails details = ProductDetails.newInstance(book);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.sell_placeholder, details )
                    .commit();
            ProductDetails productDetails = new ProductDetails();

        }

        //new sell_fragment()).commit();

    }
}