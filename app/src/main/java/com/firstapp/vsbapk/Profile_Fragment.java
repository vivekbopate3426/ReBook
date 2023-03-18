package com.firstapp.vsbapk;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile_Fragment extends Fragment {

    private Profile profile;
    private DatabaseReference ref;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private TextView moNo;
    private TextView SecondmoNO;
    private TextView address;
    private TextView edit;
    private ConstraintLayout profilelayout, createprofileLayout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile_Fragment.
     */

    // TODO: Rename and change types and number of parameters
    public static Profile_Fragment newInstance(String param1, String param2) {
        Profile_Fragment fragment = new Profile_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_profile_, container, false);
        final TextView name = view.findViewById(R.id.profile_name);
        final TextView email = view.findViewById(R.id.profile_email);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user != null) {
            name.setText(user.getDisplayName());
            email.setText(user.getEmail());
            ref = FirebaseDatabase.getInstance().getReference();

            moNo = view.findViewById(R.id.primary_mobile);
            SecondmoNO = view.findViewById(R.id.second_mobile);
            address = view.findViewById(R.id.address);
            edit = view.findViewById(R.id.profile_edit);

            profilelayout = view.findViewById(R.id.profile_layout);
            createprofileLayout = view.findViewById(R.id.Create_profile_layout);
            profile = new Profile();

        }
        ref.child("Profiles").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
              final ProgressBar ProgressBar= view.findViewById(R.id.profile_progressbar);

              private Profile profile;

              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot) {
                  if (snapshot.exists()) {
                      profile = snapshot.getValue(Profile.class);

                      moNo.setText(profile.getMono());
                      SecondmoNO.setText(profile.getSeconMono());
                      address.setText(profile.getAddress());

                      profilelayout.setVisibility(View.VISIBLE);
                      createprofileLayout.setVisibility(View.GONE);
                      ProgressBar.setVisibility(View.GONE);


                      edit.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {
                              moNo.setText(profile.getMono());
                              SecondmoNO.setText(profile.getSeconMono());
                              address.setText(profile.getAddress());


                          }
                      });

                  } else {
                      profilelayout.setVisibility(View.GONE);
                      createprofileLayout.setVisibility(View.VISIBLE);
                  }
              }

              @Override
              public void onCancelled(@NonNull DatabaseError error) {

                  final EditText MoNo = view.findViewById(R.id.profile_edit_mobile_no);
                  final EditText address = view.findViewById(R.id.profile_edit_Address);
                  final EditText secondMono = view.findViewById(R.id.profile_edit_Second_Mobile_no);
                  final TextView save = view.findViewById(R.id.profile_edit_Save);

                  save.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {

                          MoNo.setError(null);
                          address.setError(null);
                          secondMono.setError(null);
                          String MoNoText = MoNo.getText().toString();
                          String addressText = address.getText().toString();
                          String secondMONOText = secondMono.getText().toString();

                          ProgressBar.setVisibility(View.VISIBLE);

                          if (TextUtils.isEmpty(MoNoText)) {
                              MoNo.setError("Enter Mobile No.");
                          } else if (TextUtils.isEmpty(addressText)) {
                              address.setError("Enter Address");

                          } else if (TextUtils.isEmpty(secondMONOText)) {
                              secondMono.setError("Enter Second Mobile No.");

                          } else {
                          }
                          profile = new Profile();
                          profile.setMono(MoNoText);
                          profile.setAddress(addressText);
                          profile.setSeconMono(secondMONOText);
                          ref.child("Profiles").child(user.getUid()).setValue(profile)

                                  .addOnSuccessListener(new OnSuccessListener<Void>() {
                                      @Override
                                      public void onSuccess(Void unused) {
                                          Toast.makeText(getActivity(), "profile Set Successfully", Toast.LENGTH_SHORT).show();
                                          ProgressBar.setVisibility(View.GONE);
                                      }
                                  })
                                  .addOnFailureListener(new OnFailureListener() {
                                      @Override
                                      public void onFailure(@NonNull Exception e) {
                                          Toast.makeText(getActivity(), "profile Set UnSuccessfully", Toast.LENGTH_SHORT).show();
                                      }
                                  });
                      }
                  });


              }
          });
        return view;
    }
}
