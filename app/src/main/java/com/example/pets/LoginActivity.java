package com.example.pets;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private String TAG = LoginActivity.class.getSimpleName();

    private EditText editUsername;
    private  EditText editPassword;
    private Button btnLogin;
    private TextView txtsignUp;

    private String key;
    private String username;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        initView();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signInAccount();
            }
        });

        txtsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView(){
        editUsername = findViewById(R.id.username);
        editPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        txtsignUp = findViewById(R.id.signUp_text);
    }
    private boolean isValid(EditText editText, String data) {
        if (!TextUtils.isEmpty(data) && !data.equals("")) {
            return true;
        }else {
            editText.setError("This Field Cannot be Empty.");
            return false;
        }
    }

    private void signInAccount(){
        username = editUsername.getText().toString().trim();
        password = editPassword.getText().toString().trim();

        Query query = FirebaseUtils.getReference(FirebaseUtils.ACCOUNTS_PATH).orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Account account = snapshot.getValue(Account.class);
                        if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                            MyPreference.getEditorPreference().putString(MyPreference.KEY, account.getKey() );
                            MyPreference.getEditorPreference()
                                    .putBoolean(MyPreference.IS_LOGIN, true);
                            MyPreference.getEditorPreference()
                                    .putString(MyPreference.USERNAME, account.getUsername());
                            MyPreference.getEditorPreference()
                                    .putString(MyPreference.PASSWORD, account.getPassword());
                            MyPreference.getEditorPreference().commit();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, databaseError.getDetails() + " | " + databaseError.getMessage());
            }
        });
    }
}
