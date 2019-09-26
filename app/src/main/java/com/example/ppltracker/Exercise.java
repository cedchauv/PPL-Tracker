package com.example.ppltracker;

public class Exercise {

    private String name;
    private int maxSet;
    private double weight;

    public Exercise(String n, int max, double w){
        name = n;
        maxSet = max;
        weight = w;
    }

    public String getName() {
        return name;
    }

    public int getMaxSet() {
        return maxSet;
    }

    public double getWeight() {
        return weight;
    }
    public void addWeight(){
        weight += weight + 2.5;
    }
}
