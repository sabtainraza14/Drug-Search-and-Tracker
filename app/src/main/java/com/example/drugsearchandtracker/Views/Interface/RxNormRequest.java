package com.example.drugsearchandtracker.Views.Interface;

import com.example.drugsearchandtracker.Model.DrugResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RxNormRequest {

    @GET("REST/drugs.json")
    Call<DrugResponse> getDrugs(
            @Query("name") String name,
            @Query("expand") String expand
    );

    // Properties endpoint by RXCUI
    // Example: https://rxnav.nlm.nih.gov/REST/rxcui/1234/properties.json
    @GET("rxcui/{rxcui}/properties.json")
    Call<JsonObject> getDrugProperties(
            @Path("rxcui") String rxcui
    );
}
