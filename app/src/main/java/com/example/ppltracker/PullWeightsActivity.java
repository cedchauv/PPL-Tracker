package com.example.ppltracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.widget.EditText;



public class PullWeightsActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private EditText dlKG;
    private EditText bbrKG;
    private EditText pullupKG;
    private EditText cableKG;
    private EditText faceKG;
    private EditText curlKG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weights);

        prefs = getSharedPreferences("PPL_prefs.xml",Context.MODE_PRIVATE);
        editor = prefs.edit();

        dlKG = findViewById(R.id.Deadlift_Weight);
        //dlKG.addTextChangedListener(new TextListener("DL_KG", dlKG, this));
        dlKG.setText(Float.toString(prefs.getFloat("DL_KG", 70f)));

        bbrKG = findViewById(R.id.BBRow_Weight);
        //bbrKG.addTextChangedListener(new TextListener("BBR_KG", bbrKG, this));
        bbrKG.setText(Float.toString(prefs.getFloat("BBE_KG", 30f)));

        pullupKG = findViewById(R.id.pullup_weight);
        //pullupKG.addTextChangedListener(new TextListener("pullUp_KG", pullupKG, this));
        pullupKG.setText(Float.toString(prefs.getFloat("pullUp_KG", 0f)));

        cableKG = findViewById(R.id.cable_weight);
        //cableKG.addTextChangedListener(new TextListener("cableRow_KG", cableKG, this));
        cableKG.setText(Float.toString(prefs.getFloat("cableRow_KG", 40f)));

        faceKG = findViewById(R.id.face_weight);
        //faceKG.addTextChangedListener(new TextListener("face_KG", faceKG, this));
        faceKG.setText(Float.toString(prefs.getFloat("face_KG", 15f)));

        curlKG = findViewById(R.id.curl_weight);
        //curlKG.addTextChangedListener(new TextListener("curl_KG", curlKG, this));
        curlKG.setText(Float.toString(prefs.getFloat("curl_KG", 8f)));
    }

   public void writeFloat(String key, Float data){
        editor.putFloat(key, data);
        editor.apply();
    }

}
