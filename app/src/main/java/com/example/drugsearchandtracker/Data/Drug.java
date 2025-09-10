package com.example.drugsearchandtracker.Data;

public class Drug {
    private String rxcui;
    private String name;
    private boolean isLocal;

    public Drug(String rxcui, String name, boolean isLocal) {
        this.rxcui = rxcui;
        this.name = name;
        this.isLocal = isLocal;
    }
    // getters and setters
    public String getRxcui() {
        return rxcui;
    }
    public void setRxcui(String rxcui) {
        this.rxcui = rxcui;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public boolean isLocal() {
        return isLocal;
    }
    public void setLocal(boolean local) {
        isLocal = local;
    }
}
