package com.example.pets;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegistActivity extends AppCompatActivity {
    private String TAG = RegistActivity.class.getSimpleName();
    private EditText editUsername;
    private EditText editPassword;
    private Button btnSignIn;
    private TextView txtsignIn;

    private String key;
    private String username;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        initView();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAccount();
            }
        });

        txtsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        editUsername = findViewById(R.id.username);
        editPassword = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.btnsignUp);
        txtsignIn = findViewById(R.id.signIn_text);
    }

    private boolean isValid(EditText editText, String data) {
        if (!TextUtils.isEmpty(data) && !data.equals("")) {
            return true;
        } else {
            editText.setError("This Field Cannot be Empty.");
            return false;
        }
    }

    private void addAccount() {
        key = FirebaseUtils.getReference(FirebaseUtils.ACCOUNTS_PATH).push().getKey();
        username = editUsername.getText().toString().trim();
        password = editPassword.getText().toString().trim();

        if (isValid(editUsername, username) && isValid(editPassword, password)) {
            Account account = new Account(key, username, password, "", "", "", "");
            FirebaseUtils.getReference(FirebaseUtils.ACCOUNTS_PATH).child(key).setValue(account);
            Toast.makeText(RegistActivity.this,
                    "Account has been added", Toast.LENGTH_LONG);
            onBackPressed();
        }
    }
}