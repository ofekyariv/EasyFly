package com.example.m;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TripsAdapter extends ArrayAdapter<Trip> {

    Context context;
    List<Trip> trips;
    public TripsAdapter(@NonNull Context context, List<Trip> trips ) {
        super(context,R.layout.trip, trips);
        this.context=context;
        this.trips=trips;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater=((Activity)context).getLayoutInflater();
        View view= layoutInflater.inflate(R.layout.trip,parent,false);

        TextView country= (TextView)view.findViewById(R.id.country);
        TextView city= (TextView)view.findViewById(R.id.city);
        TextView attraction1= (TextView)view.findViewById(R.id.attraction1);
        TextView attraction2= (TextView)view.findViewById(R.id.attraction2);
        TextView attraction3= (TextView)view.findViewById(R.id.attraction3);
        TextView attraction4= (TextView)view.findViewById(R.id.attraction4);
        TextView budget= (TextView)view.findViewById(R.id.budget);
        TextView cost= (TextView)view.findViewById(R.id.cost);

        Trip trip= trips.get(position);

        country.setText(trip.getCountry());
        city.setText(trip.getCity());
        attraction1.setText(trip.getAttraction1());
        attraction2.setText(trip.getAttraction2());
        attraction3.setText(trip.getAttraction3());
        attraction4.setText(trip.getAttraction4());
        if(attraction1.getText().equals(""))
        {
            attraction1.setVisibility(View.GONE);
        }
        if(attraction2.getText().equals(""))
        {
            attraction2.setVisibility(View.GONE);
        }
        if(attraction3.getText().equals(""))
        {
            attraction3.setVisibility(View.GONE);
        }
        if(attraction4.getText().equals(""))
        {
            attraction4.setVisibility(View.GONE);
        }
        budget.setText(trip.getBudget() +"$");
        cost.setText(trip.getTripPrice() +"$");
        if(trip.getBudget()>=trip.getTripPrice()){
            cost.setTextColor(Color.GREEN);
        }
        else{
            cost.setTextColor(Color.RED);
        }

        return view;
    }
}
