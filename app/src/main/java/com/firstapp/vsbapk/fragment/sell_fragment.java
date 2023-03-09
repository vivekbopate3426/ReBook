package com.firstapp.vsbapk.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.text.style.TabStopSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.vsbapk.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link sell_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class sell_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public sell_fragment() {

        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment sell_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static sell_fragment newInstance(String param1, String param2) {
        sell_fragment fragment = new sell_fragment();
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
        View view = inflater.inflate(R.layout.fragment_sell_fragment, container, false);


        final EditText namefield = view.findViewById(R.id.sell_name);
        final EditText stdfield = view.findViewById(R.id.sell_standard);
        final EditText mediumfield = view.findViewById(R.id.sell_medium);
        final EditText mrpfield = view.findViewById(R.id.sell_mrp);
        final EditText pricefield = view.findViewById(R.id.sell_price);
        final EditText conditionfield = view.findViewById(R.id.sell_condition);
        final CheckBox Codfield = view.findViewById(R.id.sell_cod);
        final CheckBox returnfield = view.findViewById(R.id.sell_return);
        final EditText deliveryfield = view.findViewById(R.id.sell_delivery);
        final TextView uploadbutton = view.findViewById(R.id.sell_upload);
        final ProgressBar progressbar = view.findViewById(R.id.sell_progressbar);





        Book book = new Book();
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        uploadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressbar.setVisibility(View.VISIBLE);

                String name = namefield.getText().toString();
                String std = stdfield.getText().toString();
                String medium = mediumfield.getText().toString();
                String mrp = mrpfield.getText().toString();
                String price = pricefield.getText().toString();
                String condition = conditionfield.getText().toString();
                String delivery = deliveryfield.getText().toString();

                if (ValidateInput(name)) {
                    Toast.makeText(getActivity(), "Enter name", Toast.LENGTH_SHORT).show();
                    progressbar.setVisibility(View.GONE);
                    return;
                } else if (ValidateInput(std)) {
                    Toast.makeText(getActivity(), "Enter standard", Toast.LENGTH_SHORT).show();
                    progressbar.setVisibility(View.GONE);
                    return;
                }
                else if (ValidateInput(medium)) {
                    Toast.makeText(getActivity(), "Enter medium", Toast.LENGTH_SHORT).show();
                    progressbar.setVisibility(View.GONE);
                    return;
                }
                else if (ValidateInput(mrp)) {
                    Toast.makeText(getActivity(), "Enter mrp", Toast.LENGTH_SHORT).show();
                    progressbar.setVisibility(View.GONE);
                    return;
                }
                else if (ValidateInput(price)) {
                    Toast.makeText(getActivity(), "Enter sellPrice", Toast.LENGTH_SHORT).show();
                    progressbar.setVisibility(View.GONE);
                    return;
                }
                else   if (ValidateInput(condition)) {
                    Toast.makeText(getActivity(), "Enter condition", Toast.LENGTH_SHORT).show();
                    progressbar.setVisibility(View.GONE);
                    return;
                }
                else   if (ValidateInput(delivery)) {
                    Toast.makeText(getActivity(), "Enter dilivery", Toast.LENGTH_SHORT).show();
                    progressbar.setVisibility(View.GONE);
                    return;
                }

                book.setName(name);
                book.setStandard(std);


                book.setMeduim(medium);
                book.setMrp(mrp);
                book.setPrice(price);
                book.setCondition(condition);
                book.setCod(Codfield.isSelected());
                book.setReturnAvaiable(returnfield.isSelected());
                book.setDelivery(delivery);

                ref.child("Products").child("Books").push()
                        .setValue(book, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                progressbar.setVisibility(View.GONE);

                                if(error==null){
                    Toast.makeText(getActivity(), "Book Upload Successful", Toast.LENGTH_SHORT).show();

                }else {
                                    Toast.makeText(getActivity(), "Book Upload UnSuccessful", Toast.LENGTH_SHORT).show();

                                }
                            }
                });
                }
            private boolean ValidateInput(String name) {
                return TextUtils.isEmpty(name);
            }

        });
        return view;
    }
}
