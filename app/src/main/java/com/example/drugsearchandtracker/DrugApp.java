package com.example.drugsearchandtracker;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class DrugApp extends Application {
    private static FirebaseAuth mAuth;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize FirebaseAuth
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
    }
    public static FirebaseAuth getFirebaseAuth() {
        return mAuth;
    }

}
