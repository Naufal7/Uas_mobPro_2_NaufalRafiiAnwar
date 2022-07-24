package com.example.pets;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pets.adapter.AnimalAdapter;
import com.example.pets.adapter.RecyclerTouchListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RescueFragment extends Fragment {
    private String TAG = RescueFragment.class.getSimpleName();

    private ProgressBar loading;
    private DatabaseReference drLoker;
    private DatabaseReference drCompany;
    private SharedPreferences pref;
    private String email, userId;
    private RecyclerView rv_company;
    private ArrayList<Hewan> companyList;
    private AnimalAdapter adapterAnimals;

    public RescueFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rescue, container, false);

        pref = getActivity().getSharedPreferences("indopetrescue", Context.MODE_PRIVATE);
        drCompany = FirebaseDatabase.getInstance().getReference("hewan");

        loading = view.findViewById(R.id.loading);

        rv_company = view.findViewById(R.id.rv_animals);
        rv_company.setHasFixedSize(true);
        rv_company.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        //rv_company.setNestedScrollingEnabled(false);

        loading.setVisibility(View.VISIBLE);
        loadData();

        return view;

    }

    private void loadData() {
        if (TextUtils.isEmpty(userId)){
            userId = drCompany.push().getKey();
        }
        drCompany.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                companyList = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Hewan hewan = postSnapshot.getValue(Hewan.class);
                    companyList.add(hewan);

                }
                adapterAnimals = new AnimalAdapter(companyList, getContext());
                rv_company.setAdapter(adapterAnimals);
                RecyclerTouchListener.addTo(rv_company).setOnItemClickListener(new RecyclerTouchListener.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Intent i = new Intent(getContext(), DetailView.class);
                        //i.putExtra("pos", (Parcelable) list.get(position));

                        startActivity(i);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
