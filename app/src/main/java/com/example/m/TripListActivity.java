package com.example.m;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class TripListActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener,View.OnClickListener {

    private ListView listView;
    private ArrayList<Trip> trips;
    private TripsAdapter adapter;
    private Button btnNewTrip;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list);

        listView = findViewById(R.id.listView);
        btnNewTrip = (Button) findViewById(R.id.btnNewTrip);
        btnNewTrip.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Login Please Wait...");
        progressDialog.show();
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        DatabaseReference tripsDB = firebaseDatabase.getReference("Users/"+firebaseAuth.getUid()+"/Trips");
        trips = new ArrayList<Trip>();
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
                adapter = new TripsAdapter(TripListActivity.this, trips);
                listView.setAdapter(adapter);
                listView.setOnItemLongClickListener(TripListActivity.this);
                progressDialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TripListActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onClick(View view) {
        if (view==btnNewTrip) {
            Intent intent = new Intent(TripListActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete Trip");
        alert.setMessage("are you sure you want to delete the trip?");
        alert.setPositiveButton("delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                progressDialog.show();
                FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                String key = trips.get(position).getKey();
                DatabaseReference tripsDB = firebaseDatabase.getReference("Users/"+firebaseAuth.getUid()+"/Trips");
                tripsDB.child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        adapter.notifyDataSetChanged();
                    }
                });
                dialogInterface.dismiss();
            }
        });

        alert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.create();
        alert.show();
        adapter.notifyDataSetChanged();
        return true;
    }
}
