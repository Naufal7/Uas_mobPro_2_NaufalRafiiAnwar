package com.example.pets;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EdituserActivity extends AppCompatActivity {
    private String TAG = EdituserActivity.class.getSimpleName();


    private EditText txtusername, txtemail, txtnomor, txttwitter, txtfacebook;
    private Button butnsave, butncancel;

    private String key, password;

    private Account account;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edituser);

        initView();

        //account = (Account) getIntent().getSerializableExtra("data");

        key = MyPreference.getSharedPreferences().getString(MyPreference.KEY, null);
        password = MyPreference.getSharedPreferences().getString(MyPreference.PASSWORD, null);

        txtusername.setText(MyPreference.getSharedPreferences().getString(MyPreference.USERNAME, "username"));

        loadData();

        butnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateAcc();
                finish();

            }
        });

        butncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void loadData() {
        Query query = FirebaseUtils.getReference(FirebaseUtils.ACCOUNTS_PATH).orderByChild("key").equalTo(key);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Account account = postSnapshot.getValue(Account.class);
                    txtemail.setText(account.getEmail());
                    txtfacebook.setText(account.getFacebook());
                    txtnomor.setText(account.getNomor());
                    txttwitter.setText(account.getTwitter());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initView() {
        txtusername = findViewById(R.id.username);
        txtemail = findViewById(R.id.email);
        txtnomor = findViewById(R.id.nomor);
        txttwitter = findViewById(R.id.twitter);
        txtfacebook = findViewById(R.id.facebook);

        butnsave = findViewById(R.id.btnSave);
        butncancel = findViewById(R.id.btnCancel);
    }

    private boolean isValid(EditText editText, String data) {
        if (!TextUtils.isEmpty(data) && !data.equals("")) {
            return true;
        } else {
            editText.setError("This Field Cannot be Empty.");
            return false;
        }
    }

    private void updateAcc() {
        Account account = new Account();
        account.setKey(key);
        account.setUsername(txtusername.getText().toString().trim());
        account.setPassword(password);
        account.setEmail(txtemail.getText().toString().trim());
        account.setNomor(txtnomor.getText().toString().trim());
        account.setTwitter(txttwitter.getText().toString().trim());
        account.setFacebook(txtfacebook.getText().toString().trim());

        FirebaseUtils.getReference(FirebaseUtils.ACCOUNTS_PATH).child(key)
                .setValue(account)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EdituserActivity.this,
                                "has been updating", Toast.LENGTH_LONG).show();
                        onBackPressed();
                    }
                });

    }
}
