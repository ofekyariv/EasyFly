package com.example.m;


public abstract class ATrip implements Comparable {
    int budget;
    int tripPrice;
    String key;

    public ATrip(){
        budget=0;
        tripPrice=0;
        key="";
    }

    public int getBudget() {
        return budget;
    }
    public void setBudget(int budget) {
        this.budget=budget;
    }
    public int getTripPrice() {
        return tripPrice;
    }
    public void setTripPrice(int tripPrice) {
        this.tripPrice=tripPrice;
    }
    public void setKey(String key) {
        this.key=key;
    }
    public String getKey() {
        return key;
    }

    @Override
    public abstract int compareTo(Object o);
}
