package com.example.pets;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {
    private String TAG = ProfileFragment.class.getSimpleName();


    private TextView txt_Username, txt_Email, txt_Nomor, txt_Twitter, txt_Facebook;
    private ImageView imgLogout, imgSetting;
    private String key;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        initView(view);

        txt_Username.setText(MyPreference.getSharedPreferences().getString(MyPreference.USERNAME, "username"));

        key = MyPreference.getSharedPreferences().getString(MyPreference.KEY, null);


        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPreference.getEditorPreference().clear().commit();
                Intent intent = new Intent(getActivity(), SplashscreenActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EdituserActivity.class);
                startActivity(intent);
            }
        });

        loadData();

        return view;
    }

    private void loadData() {
        Query query = FirebaseUtils.getReference(FirebaseUtils.ACCOUNTS_PATH).orderByChild("key").equalTo(key);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Account account = postSnapshot.getValue(Account.class);
                    txt_Email.setText(account.getEmail());
                    txt_Facebook.setText(account.getFacebook());
                    txt_Nomor.setText(account.getNomor());
                    txt_Twitter.setText(account.getTwitter());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initView(View view){
        txt_Username = view.findViewById(R.id.username);
        txt_Email = view.findViewById(R.id.txt_email);
        txt_Nomor = view.findViewById(R.id.txt_nomor);
        txt_Twitter = view.findViewById(R.id.txt_twitter);
        txt_Facebook = view.findViewById(R.id.txt_facebook);

        imgLogout = view.findViewById(R.id.img_logout);
        imgSetting = view.findViewById(R.id.img_setting);
    }

}
