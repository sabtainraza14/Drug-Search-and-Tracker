package com.example.drugsearchandtracker.Views.Interface;

import com.example.drugsearchandtracker.Data.Drug;
import com.example.drugsearchandtracker.Model.DrugModel;

import java.util.List;

public interface DrugCallBack {
    void onSuccess(List<Drug> drugs);
    void onError(String errorMsg);
}
