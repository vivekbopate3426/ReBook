package com.firstapp.vsbapk.fragment;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firstapp.vsbapk.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetails extends Fragment {
    private Book book;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment ProductDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductDetails newInstance(Book book) {
        ProductDetails fragment = new ProductDetails();
        Bundle args = new Bundle();
        args.putSerializable("Book",book);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
            book = (Book) getArguments().getSerializable("Book");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        final TextView name = view.findViewById(R.id.pd_name);
        name.setText(book.getName());

        final TextView sellPrice = view.findViewById(R.id.pd_sellprice);

        final TextView sellMrp = view.findViewById(R.id.pd_MRP);

        final TextView sellDiscount = view.findViewById(R.id.pd_discount);

        sellPrice.setText("₹"+book.getPrice());
        sellMrp.setText("₹"+book.getMrp());

        final TextView replace = view.findViewById(R.id.pd_return);
        final TextView cod  = view.findViewById(R.id.pd_cod);

        String replaceString = book.isReturnAvaiable()?
                "Replace in 7 days " : "Non returnable";
        replace.setText(replaceString);
        String codString = book.isCod()?"COD Available" : "COD NOT Available";
        cod.setText(codString);


        int mrpInt = Integer.parseInt(book.getMrp());
        int sellInt = Integer.parseInt(book.getPrice());

        int difference = mrpInt-sellInt;
        int discountInt = (difference*100)/mrpInt;
        sellDiscount.setText(discountInt+"%");
        sellMrp.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        return view;
    }
}