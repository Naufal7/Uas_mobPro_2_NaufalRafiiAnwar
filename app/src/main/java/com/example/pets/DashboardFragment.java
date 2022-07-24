package com.example.pets;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class DashboardFragment extends Fragment {
    private String TAG = DatabaseReference.class.getSimpleName();

    private TextView txtVIew;
    private CardView cardAdop, cardCall;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        initView(view);

        cardAdop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RescueActivity.class);
                startActivity(intent);
            }
        });
        cardCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:08982452013"));
                startActivity(intent);
            }
        });

        return view;

    }

    private void initView(View view) {
        txtVIew = view.findViewById(R.id.txt_dashboard);
        cardAdop = view.findViewById(R.id.petscardview);
        cardCall = view.findViewById(R.id.card_call);
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUtils.getReference(FirebaseUtils.MYNAME_PATH).addValueEventListener(new ValueEventListener() {




            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txtVIew.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
