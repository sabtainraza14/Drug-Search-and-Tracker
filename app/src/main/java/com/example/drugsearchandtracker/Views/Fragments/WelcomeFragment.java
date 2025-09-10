package com.example.drugsearchandtracker.Views.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.drugsearchandtracker.R;
import com.example.drugsearchandtracker.databinding.FragmentWelcomeBinding;

public class WelcomeFragment extends Fragment {

    private FragmentWelcomeBinding binding;
    private Button signUpBtn;

    private TextView haveAccount;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWelcomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signUpBtn = binding.btnSignUp;
        signUpBtn.setOnClickListener(v ->
                loadFragment(new SignUpFragment())
        );

        haveAccount = binding.tvHaveAccount;
        haveAccount.setOnClickListener(v -> {
            loadFragment(new LoginFragment());
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_welcome, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}