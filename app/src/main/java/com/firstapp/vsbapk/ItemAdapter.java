package com.firstapp.vsbapk;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.database.paging.DatabasePagingOptions;
import com.firebase.ui.database.paging.FirebaseRecyclerPagingAdapter;
import com.firstapp.vsbapk.fragment.Book;

//step4............................................

public class ItemAdapter extends FirebaseRecyclerPagingAdapter<Book,ItemViewHolder> {
    private final ClickListener mclickListener;



    /**
     * Construct a new FirestorePagingAdapter from the given {@link DatabasePagingOptions}.
     *
     * @param options
     * @param mclickListener
     */
    public ItemAdapter(@NonNull DatabasePagingOptions<Book> options, ClickListener mclickListener) {
        super(options);
        this.mclickListener = mclickListener;
    }



    @Override
    protected void onBindViewHolder(@NonNull ItemViewHolder viewHolder, int position, @NonNull Book model) {
        viewHolder.bind(model);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mclickListener.onItemClick(viewHolder.getAbsoluteAdapterPosition(),model);

            }

        });

    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_layout,parent,false);
        return new ItemViewHolder(view);
    }
    public interface ClickListener{
        void onItemClick(int position, Book book);

    }
}
