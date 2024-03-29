package com.firstapp.vsbapk;

import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Shopping_Cart_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Shopping_Cart_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FirebaseAuth auth;

    private FirebaseUser user;

    private FirebaseRecyclerAdapter adapter;

    public Shopping_Cart_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Shopping_Cart_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Shopping_Cart_Fragment newInstance(String param1, String param2) {
        Shopping_Cart_Fragment fragment = new Shopping_Cart_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_shopping__cart_, container, false);
        final RecyclerView recyclerView = view.findViewById(R.id.cart_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (adapter!=null);
        recyclerView.setAdapter(adapter);



        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user != null) {
            Query query = FirebaseDatabase.getInstance().getReference().child("Cart").child(user.getUid());
            FirebaseRecyclerOptions<CartItem> options = new FirebaseRecyclerOptions.Builder<CartItem>()
                    .setQuery(query, CartItem.class)
                    .setLifecycleOwner(this).build();

            adapter = new FirebaseRecyclerAdapter<CartItem,CartHolder>(options) {

                @NonNull
                @Override
                public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    return new CartHolder(LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.cartitem, parent, false));
                }


                @Override
                protected void onBindViewHolder(@NonNull CartHolder holder, int position, @NonNull CartItem model) {
                    holder.bind(model);
                }



            };
        }
    }

}