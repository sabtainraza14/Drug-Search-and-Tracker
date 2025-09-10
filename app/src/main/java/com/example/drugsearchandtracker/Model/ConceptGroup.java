package com.example.drugsearchandtracker.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConceptGroup {
    @SerializedName("tty") public String tty;
    @SerializedName("conceptProperties") public List<ConceptProperties> conceptProperties;
}
