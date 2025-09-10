package com.example.drugsearchandtracker.Views.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drugsearchandtracker.Controllers.SearchController;
import com.example.drugsearchandtracker.Data.Drug;
import com.example.drugsearchandtracker.Views.Interface.DrugCallBack;
import com.example.drugsearchandtracker.R;
import com.example.drugsearchandtracker.Views.Adaptor.DrugAdapter;
import com.example.drugsearchandtracker.databinding.FragmentSearchBinding;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private EditText etSearch;
    private Button btnSearch;
    private RecyclerView recyclerView;
    private DrugAdapter adapter;
    private SearchController controller;
    private List<Drug> currentList = new ArrayList<>();
    private TextView btnBack;

    public SearchFragment() {
    }
    @Override
    public void onResume() {
        super.onResume();
//        mainActivity.loadSavedDrugs();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnBack = binding.tvBack;
        etSearch = binding.etSearch;
        btnSearch = binding.btnSearch;
        recyclerView = binding.recyclerView;

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DrugAdapter(currentList, drug -> {
            Bundle bundle = new Bundle();
            bundle.putString("rxcui", drug.getRxcui());
            bundle.putString("name", drug.getName());

            DrugDetailFragment fragment = new DrugDetailFragment();
            fragment.setArguments(bundle);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_searchMedicine, fragment)
                    .addToBackStack(null)
                    .commit();
        });

        recyclerView.setAdapter(adapter);
        controller = new SearchController(requireContext());

        btnSearch.setOnClickListener(v -> {
            String query = etSearch.getText().toString().trim();
            if (query.isEmpty()) {
                Toast.makeText(requireContext(), "Enter drug name", Toast.LENGTH_SHORT).show();
            } else {
                binding.progressBar.setVisibility(View.VISIBLE);
                controller.searchDrugByName(query, new DrugCallBack() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onSuccess(List<Drug> drugs) {
                        if (getActivity() == null) return;
                        getActivity().runOnUiThread(() -> {
                            currentList.clear();
                            currentList.addAll(drugs);
                            adapter.notifyDataSetChanged();
                            binding.progressBar.setVisibility(View.GONE);
                        });
                    }
                    @Override
                    public void onError(String msg) {
                        if (getActivity() == null) return;
                        getActivity().runOnUiThread(() ->
                                Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show()
                        );
                        binding.progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });
        btnBack.setOnClickListener(v -> {
            if (requireActivity().getSupportFragmentManager().getBackStackEntryCount() > 0) {
                requireActivity().getSupportFragmentManager().popBackStack();
            } else {
                //If no fragment then back to mainActivity
                requireActivity().finish();
            }
        });

    }
}