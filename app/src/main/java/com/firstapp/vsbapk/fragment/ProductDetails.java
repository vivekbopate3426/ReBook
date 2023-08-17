package com.firstapp.vsbapk.fragment;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.vsbapk.CartItem;
import com.firstapp.vsbapk.R;
import com.firstapp.vsbapk.Shopping_Cart_Fragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetails extends Fragment {
    private Book book;
    private ProgressBar progressBar;

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
     *
     * @return A new instance of fragment ProductDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductDetails newInstance(Book book) {
        ProductDetails fragment = new ProductDetails();
        Bundle args = new Bundle();
        args.putSerializable("Book", book);
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
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);

        final TextView seller = view.findViewById(R.id.seller);
        final TextView medium = view.findViewById(R.id.medium);
        final TextView standard = view.findViewById(R.id.standard);
        final TextView condition = view.findViewById(R.id.condition);
        final TextView delivery = view.findViewById(R.id.delivery);
        final TextView name = view.findViewById(R.id.pd_name);
        final TextView sellPrice = view.findViewById(R.id.pd_sellprice);
        final TextView sellMrp = view.findViewById(R.id.pd_MRP);
        final TextView sellDiscount = view.findViewById(R.id.pd_discount);
        progressBar = view.findViewById(R.id.pd_progressbar);

        name.setText(book.getName());
        sellPrice.setText("₹" + book.getPrice());
        sellMrp.setText("₹" + book.getMrp());
        medium.setText(book.getMeduim());
        standard.setText(book.getStandard());
        condition.setText(book.getCondition());
        delivery.setText(book.getDelivery());

        final TextView replace = view.findViewById(R.id.pd_return);
        final TextView cod = view.findViewById(R.id.pd_cod);


        String replaceString = book.isReturnAvaiable() ?
                "Replace in 7 days " : "Non returnable";
        replace.setText(replaceString);
        String codString = book.isCod() ? "COD Available" : "COD NOT Available";
        cod.setText(codString);


        int mrpInt = Integer.parseInt(book.getMrp());
        int sellInt = Integer.parseInt(book.getPrice());

        int difference = mrpInt - sellInt;
        int discountInt = (difference * 100) / mrpInt;
        sellDiscount.setText(discountInt + "%");
        sellMrp.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        final TextView addtocartbutton = view.findViewById(R.id.pd_addcart);
        addtocartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                if (user != null) {
                    String username = user.getDisplayName();
                    String userId = user.getUid();
                    CartItem cartItem = new CartItem();
                    cartItem.setProductName(book.getName() + "_" + book.getStandard() + "_" + book.getMeduim());
                    cartItem.setSellername(book.getSeller());
                    cartItem.setMrp(Integer.parseInt(book.getMrp()));
                    cartItem.setPrice(Integer.parseInt(book.getPrice()));
                    cartItem.setQuantity(1);
                    cartItem.setAmount(cartItem.getPrice() * cartItem.getQuantity());
                    SaveToCart(cartItem,userId);

                }
            }
        });


        return view;
    }

    private void SaveToCart(CartItem cartItem, String userId) {

        progressBar.setVisibility(View.VISIBLE);
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
        reference.child("Cart").child(userId).push().setValue(cartItem, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error == null) {

                    Toast.makeText(getActivity(), "Add to Cart", Toast.LENGTH_SHORT).show();
                    writeCartTotal(userId,cartItem.getAmount());
                    //   reference.child("CartTotal").child(userId).setValue(FirebaseDatabase.ServerValue.increment(amount));
                }
            }

        });
    }

    private void writeCartTotal(String userId,int amount) {
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference();
        reference.child("CartTotal").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int total=0;
                if (snapshot.exists()){
                    total=snapshot.getValue(Integer.class);
                    total=total+amount;
                }else{
                    total=amount;
                }
                snapshot.getRef().setValue(total);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
