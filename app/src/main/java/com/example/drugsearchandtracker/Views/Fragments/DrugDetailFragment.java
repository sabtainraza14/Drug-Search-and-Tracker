package com.example.drugsearchandtracker.Views.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drugsearchandtracker.Controllers.SearchController;
import com.example.drugsearchandtracker.Data.Drug;
import com.example.drugsearchandtracker.databinding.FragmentDrugDetailBinding;


public class DrugDetailFragment extends Fragment {

    private FragmentDrugDetailBinding binding;
    private TextView tvRxcui;
    private TextView tvName;
    private Button saveBtn;
    private SearchController controller;

    private String name;

    private String rxcui;

    private TextView backBtn;


    public DrugDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDrugDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvName = binding.tvName;
        tvRxcui = binding.tvRxcui;
        saveBtn = binding.btnSave;
        backBtn = binding.tvBack;

        controller = new SearchController(requireContext());

        if (getArguments() != null) {
            name = getArguments().getString("name");
            rxcui = getArguments().getString("rxcui");

            tvName.setText(name);
            tvRxcui.setText(rxcui);
        }

        saveBtn.setOnClickListener(v -> {
            Drug drug = new Drug(rxcui, name, true);
            String result = controller.saveDrug(drug);
            Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
        });

        backBtn.setOnClickListener(v -> {
            if (requireActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
                requireActivity().getSupportFragmentManager().popBackStack();
            } else {
                //If no fragment then back to mainActivity
                requireActivity().finish();
            }

        });
    }
}