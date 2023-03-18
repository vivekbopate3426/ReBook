package com.firstapp.vsbapk;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.vsbapk.fragment.Book;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private TextView titleTextView;
    private TextView bodyTextView;

    public ItemViewHolder(View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.product_name);
        bodyTextView = itemView.findViewById(R.id.product_description);
    }

    public void bind(Book item) {
        titleTextView.setText(item.getName());
        bodyTextView.setText(item.getMrp());




    }
}
