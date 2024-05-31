package com.mirea.kt.ribo.datastorage2_10;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBManager {
    private SQLiteOpenHelper sqLiteOpenHelper;

    public DBManager(SQLiteOpenHelper sqLiteOpenHelper) {
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }
    public boolean saveCarToDatabase(Car car) {
        SQLiteDatabase db = this.sqLiteOpenHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("model", car.getModel());
        cv.put("number", car.getNumber());
        cv.put("year", car.getYear());
        long rowId = db.insert("TABLE_CARS", null, cv);
        cv.clear();
        db.close();

        return rowId != -1;
    }
    public ArrayList<Car> loadAllCarsFromDatabase() {
        ArrayList<Car> persons = new ArrayList<>();
        SQLiteDatabase db = this.sqLiteOpenHelper.getWritableDatabase();
        Cursor dbCursor = db.query("TABLE_CARS",
                null, null, null, null, null, null);

        if (dbCursor.moveToFirst()) {
            do {
                String model = dbCursor.getString(dbCursor.getColumnIndexOrThrow("model"));
                String number = dbCursor.getString(dbCursor.getColumnIndexOrThrow("number"));
                int year = dbCursor.getInt(dbCursor.getColumnIndexOrThrow("year"));
                persons.add(new Car(model,number,year));
            } while (dbCursor.moveToNext());
        }

        dbCursor.close();
        db.close();
        return persons;
    }

}
