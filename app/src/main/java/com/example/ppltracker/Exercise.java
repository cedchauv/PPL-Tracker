package com.example.ppltracker;

public class Exercise {

    private String name;
    private int maxSet;
    private float weight;
    private String key;


    public Exercise(String n, int max, float w, String wKey){
        name = n;
        maxSet = max;
        weight = w;
        key = wKey;
    }

    public String getKey(){ return key; }

    public String getName() {
        return name;
    }

    public int getMaxSet() {
        return maxSet;
    }

    public float getWeight() {
        return weight;
    }

}
