package com.example.drugsearchandtracker.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class DrugDao {

    private DBHelper helper;

    public DrugDao(Context context) {
        helper = new DBHelper(context);
    }

    public long addDrug(Drug drug) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("rxcui", drug.getRxcui());
        cv.put("name", drug.getName());
        cv.put("is_local", drug.isLocal() ? 1 : 0);
        long id = db.insert("user_drugs", null, cv);
        db.close();
        return id;
    }

    public List<Drug> getAllDrugs() {
        List<Drug> list = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT rxcui, name, is_local FROM user_drugs", null);
        while (c.moveToNext()) {
            String rxcui = c.getString(0);
            String name = c.getString(1);
            boolean isLocal = c.getInt(2) == 1;
            Drug d = new Drug(rxcui, name, isLocal);
            list.add(d);
        }
        c.close();
        db.close();
        return list;
    }

    public void deleteByName(String name) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("user_drugs", "name = ?", new String[]{name});
        db.close();
    }

    public int countLocal() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT COUNT(*) FROM user_drugs WHERE is_local=1", null);
        int count = 0;
        if (c.moveToFirst()) count = c.getInt(0);
        c.close();
        db.close();
        return count;
    }

    public int countApiAdded() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT COUNT(*) FROM user_drugs WHERE is_local=0", null);
        int count = 0;
        if (c.moveToFirst()) count = c.getInt(0);
        c.close();
        db.close();
        return count;
    }
}
