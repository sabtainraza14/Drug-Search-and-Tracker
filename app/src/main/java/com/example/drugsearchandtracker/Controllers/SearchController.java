package com.example.drugsearchandtracker.Controllers;

import android.content.Context;

import androidx.annotation.NonNull;
import com.example.drugsearchandtracker.Data.Drug;
import com.example.drugsearchandtracker.Data.DrugDao;
import com.example.drugsearchandtracker.Model.ConceptGroup;
import com.example.drugsearchandtracker.Model.ConceptProperties;
import com.example.drugsearchandtracker.Model.DrugResponse;
import com.example.drugsearchandtracker.Views.Interface.DrugCallBack;
import com.example.drugsearchandtracker.Views.Interface.RxNormRequest;
import com.example.drugsearchandtracker.network.ApiClient;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class SearchController {
    private static final String TAG = "SearchController";
    private RxNormRequest api;

    private DrugDao dao;

    private Context context;
    public SearchController(Context context) {
        this.context = context;
        api = ApiClient.getClient();
        dao = new DrugDao(context);
    }

    public void searchDrugByName(String name, DrugCallBack callback) {
        api.getDrugs(name, "psn").enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<DrugResponse> call, @NonNull Response<DrugResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Drug> list = mapToDrug(response.body());
                    callback.onSuccess(list);
                } else {
                    callback.onError("No data found");
                }
            }
            @Override
            public void onFailure(@NonNull Call<DrugResponse> call, @NonNull Throwable t) {
                callback.onError("API Error: " + t.getMessage());
            }
        });
    }
    private List<Drug> mapToDrug(DrugResponse resp) {
        List<Drug> drugs = new ArrayList<>();
        if (resp == null || resp.drugGroup == null || resp.drugGroup.conceptGroup == null) {
            return drugs;
        }
        for (ConceptGroup group : resp.drugGroup.conceptGroup) {
            if (group.conceptProperties != null) {
                for (ConceptProperties p : group.conceptProperties) {
                    if (drugs.size() >= 7) break;
                    drugs.add(new Drug(p.rxcui, p.name, false));
                }
            }
        }
        return drugs;
    }
    public String saveDrug(Drug drug) {
        long result = dao.addDrug(drug);
        if (result > 0) {
            return "Saved Successfully!";
        } else {
            return "Save Failed!";
        }
    }
    public List<Drug> getAllSavedDrugs() {
        return dao.getAllDrugs();
    }

    public void deleteDrug(String rxcui)
    {
        dao.deleteByName(rxcui);
    }

}
