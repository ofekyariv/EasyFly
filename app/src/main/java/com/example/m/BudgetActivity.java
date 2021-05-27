package com.example.m;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BudgetActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnBack,btnNext;
    EditText etBudget;
    public static Trip trip = new Trip();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        etBudget = findViewById(R.id.etBudget);

    }

    @Override
    protected void onResume() {
        super.onResume();
        trip=new Trip();
    }

    @Override
    public void onClick(View view) {
        if (view==btnNext){
            int budget = 0;
            try{
                budget = Integer.parseInt(etBudget.getText().toString());
            }
            catch (Exception ignored){}
            trip.setBudget(budget);
            Toast.makeText(this, trip.getBudget() + " Selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(BudgetActivity.this,WhereActivity.class);
            startActivity(intent);
            finish();
        }

        if (view==btnBack){
            Intent intent = new Intent(BudgetActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}