package com.mirea.kt.ribo.datastorage2_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etModel, etNumber, etYear;
    private Button btnAdd, btnView;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etModel = findViewById(R.id.etModel);
        etNumber = findViewById(R.id.etNumber);
        etYear = findViewById(R.id.etYear);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        btnAdd.setOnClickListener(this);
        btnView.setOnClickListener(this);
        this.dbManager = new DBManager(new MyAppSQLiteHelper(this,"my_database.db",null,1));


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAdd) {
            if (this.dbManager != null) {
                String model = etModel.getText().toString();
                String number = etNumber.getText().toString();
                String year = etYear.getText().toString();
                if (!model.isEmpty() && !number.isEmpty() && !year.isEmpty()) {
                    boolean result = dbManager.saveCarToDatabase(new Car(model, number, Integer.parseInt(year)));
                    if (result) {
                        Toast.makeText(this, "insert success", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "insert error", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "incorrect value", Toast.LENGTH_LONG).show();
                }
            }
        } else if (v.getId() == R.id.btnView) {
            startActivity(new Intent(this,CarActivity.class));
        }
    }
}