package com.firstapp.vsbapk.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.paging.PagingConfig;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.paging.DatabasePagingOptions;
import com.firstapp.vsbapk.ItemAdapter;
import com.firstapp.vsbapk.R;
import com.firstapp.vsbapk.SellActivity;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Home_Fragment newInstance(String param1, String param2) {
        Home_Fragment fragment = new Home_Fragment();
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

       View view = inflater.inflate(R.layout.fragment_home_, container, false);

        final RecyclerView recyclerView = view.findViewById(R.id.recycleView_home);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        Query query = FirebaseDatabase.getInstance().getReference().child("Products").child("Books");
        PagingConfig config = new PagingConfig(10,5,false);
        DatabasePagingOptions<Book>options= new DatabasePagingOptions.Builder<Book>()
                .setLifecycleOwner(this)
                .setQuery(query,config,Book.class)
                .build();

        ItemAdapter adapter = new ItemAdapter(options, new ItemAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, Book book) {
                Intent intent = new Intent(getActivity(), SellActivity.class);
                intent.putExtra("Book",book);
                intent.putExtra("CALLER","ProductDetails");
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);





        // Inflate the layout for this fragment
        final TextView sellbutton = view.findViewById(R.id.home_sell);
        sellbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SellActivity.class);
                intent.putExtra("CALLER","sell");
                getActivity().startActivity(intent);

            }
        });
        return view;


    }
}