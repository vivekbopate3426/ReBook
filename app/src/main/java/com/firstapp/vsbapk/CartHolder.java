package com.firstapp.vsbapk;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.vsbapk.CartItem;


public class CartHolder extends RecyclerView.ViewHolder {

    private final TextView name;

    //private final TextView std,medium;
    private final TextView sellername;
    private final TextView price;
    private final TextView delivery;
    private final TextView amount;

    public CartHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.cart_name);
        //std,medium = itemView.findViewById(R.id.cart_std_medium);
        sellername = itemView.findViewById(R.id.cart_sellername);
        price = itemView.findViewById(R.id.cart_price);
        delivery = itemView.findViewById(R.id.cart_delivery);
       amount = itemView.findViewById(R.id.cart_amount);
    }
    public void bind(@NonNull CartItem item){
        name.setText(item.getProductName());
        //std,medium.setText(item.);
        sellername.setText(item.getSellername());
        price.setText("₹"+item.getSellername());
        delivery.setText("Free");
        amount.setText("₹"+item.getAmount());
        //amount.setText(item.getAmount());
    }
}
