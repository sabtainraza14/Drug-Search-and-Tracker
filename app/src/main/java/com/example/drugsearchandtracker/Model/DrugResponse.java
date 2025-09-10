package com.example.drugsearchandtracker.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DrugResponse {

    @SerializedName("drugGroup")
    public DrugGroup drugGroup;

    public static class DrugGroup {
        @SerializedName("conceptGroup")
        public List<ConceptGroup> conceptGroup;
    }
}

