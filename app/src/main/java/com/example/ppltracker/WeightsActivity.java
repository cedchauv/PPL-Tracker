package com.example.ppltracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.widget.EditText;



public class WeightsActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private EditText dlKG;
    private EditText bbrKG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weights);

        prefs = getSharedPreferences("PPL_prefs.xml",Context.MODE_PRIVATE);
        editor = prefs.edit();

        dlKG = findViewById(R.id.Deadlift_Weight);
        dlKG.addTextChangedListener(new TextListener("DL_KG", dlKG, this));
        dlKG.setText(Float.toString(prefs.getFloat("DL_KG", 70f)));

        bbrKG = findViewById(R.id.BBRow_Weight);
        bbrKG.addTextChangedListener(new TextListener("BBR_KG", bbrKG, this));
        bbrKG.setText(Float.toString(prefs.getFloat("BBE_KG", 30f)));

    }

   public void writeFloat(String key, Float data){
        editor.putFloat(key, data);
        editor.commit();
    }

}
