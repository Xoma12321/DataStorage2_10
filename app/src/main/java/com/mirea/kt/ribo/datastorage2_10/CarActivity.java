package com.mirea.kt.ribo.datastorage2_10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class CarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        DBManager dbManager = new DBManager(new MyAppSQLiteHelper(this,"my_database.db",null,1));
        RecyclerView rcView = findViewById(R.id.recyclerView);
        CarAdapter adapter = new CarAdapter(dbManager.loadAllCarsFromDatabase());
        rcView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rcView.setAdapter(adapter);
    }
}