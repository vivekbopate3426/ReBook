package com.firstapp.vsbapk;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.paging.DatabasePagingOptions;
import com.firebase.ui.database.paging.FirebaseRecyclerPagingAdapter;
import com.firebase.ui.database.paging.LoadingState;
import com.firstapp.vsbapk.R;
import com.firstapp.vsbapk.fragment.Book;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

//step4............................................
public class ItemAdapter extends FirebaseRecyclerPagingAdapter<Book, ItemViewHolder> {


    /**
     * Construct a new FirestorePagingAdapter from the given {@link DatabasePagingOptions}.
     *
     * @param options
     */
    public ItemAdapter(@NonNull DatabasePagingOptions<Book> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ItemViewHolder viewHolder, int position, @NonNull Book model) {

    }

    @Override
    protected void onLoadingStateChanged(@NonNull LoadingState state) {

    }
}
