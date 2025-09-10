package com.example.drugsearchandtracker.Views.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drugsearchandtracker.Controllers.SearchController;
import com.example.drugsearchandtracker.Data.Drug;
import com.example.drugsearchandtracker.Views.Adaptor.DrugAdapter;
import com.example.drugsearchandtracker.Views.Fragments.DrugDetailFragment;
import com.example.drugsearchandtracker.Views.Fragments.SearchFragment;
import com.example.drugsearchandtracker.R;
import com.example.drugsearchandtracker.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SearchController controller;
    private List<Drug> savedList = new ArrayList<>();
    private DrugAdapter adapter;

    private ItemTouchHelper touchHelper;

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = binding.recyclerView;
        controller = new SearchController(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DrugAdapter(savedList, drug -> {
//            loadFragment(new DrugDetailFragment("MainActivity"));
        });
        recyclerView.setAdapter(adapter);

        binding.tvSearchBtn.setOnClickListener(v -> {
            loadFragment(new SearchFragment());
        });

        swipeToDelete();

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                loadSavedDrugs(); // refreshList
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                } else {
                    finish();
                }
            }
        });


    }

    private void swipeToDelete()
    {
        touchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {
                        return false; //For Drag and Drop
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Drug drug = savedList.get(position);

                        // Delete from DB
                        controller.deleteDrug(drug.getName());

                        // Delete from List
                        savedList.remove(position);
                        adapter.notifyItemRemoved(position);
                    }
                }
        ); touchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSavedDrugs();
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @SuppressLint("NotifyDataSetChanged")
    public void loadSavedDrugs() {
        savedList.clear();
        savedList.addAll(controller.getAllSavedDrugs());
        adapter.notifyDataSetChanged();
    }


}