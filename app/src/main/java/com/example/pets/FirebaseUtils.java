package com.example.pets;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils {
    public static final String MYNAME_PATH = "myname";
    public static final String ACCOUNTS_PATH = "accounts";
    public static final String HEWAN_PATH = "hewan";

    private static final  FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    public static DatabaseReference getReference(String path){
        return firebaseDatabase.getReference(path);
    }
}
