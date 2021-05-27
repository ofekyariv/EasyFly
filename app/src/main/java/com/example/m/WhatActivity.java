package com.example.m;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.m.BudgetActivity.trip;

public class WhatActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnNext,btnBack;
    CheckBox boxCar,boxMuseum,boxCasino,boxDining;
    private FirebaseAuth firebaseAuth;
    private ArrayList<Trip> trips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        boxCar = (CheckBox) findViewById(R.id.boxCar);
        boxMuseum = (CheckBox) findViewById(R.id.boxMuseum);
        boxCasino = (CheckBox) findViewById(R.id.boxCasino);
        boxDining = (CheckBox) findViewById(R.id.boxDining);
        firebaseAuth= FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if (view==btnNext){
            if(boxCar.isChecked()){
                trip.setAttraction1("Car");
            }
            else{
                trip.setAttraction1("");
            }
            if(boxMuseum.isChecked()){
                trip.setAttraction2("Museum");
            }
            else{
                trip.setAttraction2("");
            }
            if(boxCasino.isChecked()){
                trip.setAttraction3("Casino");
            }
            else{
                trip.setAttraction3("");
            }
            if(boxDining.isChecked()){
                trip.setAttraction4("Dining");
            }
            else{
                trip.setAttraction4("");
            }
            trip.calculateCost();

            Toast.makeText(this, trip.getAttraction1() + ", " + trip.getAttraction2() +", " + trip.getAttraction3() + ", " + trip.getAttraction4() + " Selected", Toast.LENGTH_SHORT).show();
            newTrip(trip);
            Intent intent = new Intent(WhatActivity.this,TripListActivity.class);
            startActivity(intent);
        }

        if (view==btnBack){
            Intent intent = new Intent(WhatActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
    private void newTrip(Trip trip) {
        if(!IfTripExist(trip)) {
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseAuth = FirebaseAuth.getInstance();
            DatabaseReference db = firebaseDatabase.getReference("Users/" + firebaseAuth.getUid() + "/Trips").push();
            db.setValue(trip);
        }
    }
    private boolean IfTripExist(Trip trip){
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference tripsDB = firebaseDatabase.getReference("Users/"+firebaseAuth.getUid()+"/Trips");
        trips = new ArrayList<>();
        tripsDB.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                trips.clear();
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    Trip trip = new Trip();
                    trip.setAttraction1(data.child("attraction1").getValue().toString());
                    trip.setAttraction2(data.child("attraction2").getValue().toString());
                    trip.setAttraction3(data.child("attraction3").getValue().toString());
                    trip.setAttraction4(data.child("attraction4").getValue().toString());
                    trip.setCountry(data.child("country").getValue().toString());
                    trip.setCity(data.child("city").getValue().toString());
                    trip.setBudget(Integer.parseInt(data.child("budget").getValue().toString()));
                    trip.setTripPrice(Integer.parseInt(data.child("tripPrice").getValue().toString()));
                    trip.setKey(data.getKey());
                    trips.add(trip);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        for (Trip t:trips) {
            if(trip.compareTo(t) > 0){
                return true;
            }
        }
        return false;
    }
}