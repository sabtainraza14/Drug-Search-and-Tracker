package com.example.drugsearchandtracker.Views.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.drugsearchandtracker.DrugApp;
import com.example.drugsearchandtracker.Model.User;
import com.example.drugsearchandtracker.databinding.FragmentSignUpBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUpFragment extends Fragment {

    private FragmentSignUpBinding binding;
    private EditText edName;
    private EditText edEmail;
    private EditText edPassword;
    private Button btnCreateUser;

    FirebaseAuth mAuth = DrugApp.getFirebaseAuth();


    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edName = binding.edName;
        edEmail = binding.edEmail;
        edPassword = binding.edCreatePassword;

        btnCreateUser = binding.btnSignUp;

        btnCreateUser.setOnClickListener(v -> {

            String name = edName.getText().toString().trim();
            String email = edEmail.getText().toString().trim();
            String password = edPassword.getText().toString().trim();
            registerUser(name, email, password);

        });

    }

    private void registerUser(String name, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Get current user UID
                        assert mAuth.getCurrentUser() != null;
                        String uid = mAuth.getCurrentUser().getUid();

                        // Create User object
                        User user = new User(name, email, password);

                        // Save to Realtime Database
                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(uid)
                                .setValue(user)
                                .addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()) {
                                        Toast.makeText(requireContext(), "User registered & saved!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(requireContext(), "Failed to save user: " + Objects.requireNonNull(task1.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        Toast.makeText(requireContext(), "Auth failed: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}