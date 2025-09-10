package com.example.drugsearchandtracker.Views.Adaptor;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.drugsearchandtracker.Data.Drug;
import com.example.drugsearchandtracker.Views.Interface.OnItemClickListener;
import com.example.drugsearchandtracker.databinding.ItemMedicineBinding;

import java.util.List;

public class DrugAdapter extends RecyclerView.Adapter<DrugAdapter.DrugViewHolder> {

    private List<Drug> item;

    private OnItemClickListener listener;

    public DrugAdapter(List<Drug> item, OnItemClickListener listener) {
        this.item = item;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DrugViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemMedicineBinding binding = ItemMedicineBinding.inflate(inflater, parent, false);
        return new DrugViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DrugViewHolder holder, int position) {

        Drug drug = item.get(position);
        holder.rxcui.setText(drug.getRxcui());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(drug));
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    static class DrugViewHolder extends RecyclerView.ViewHolder {
        TextView rxcui;
        public DrugViewHolder(@NonNull ItemMedicineBinding binding) {
            super(binding.getRoot());
            rxcui = binding.tvMedicineName;
        }
    }

}
