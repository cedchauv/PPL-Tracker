package com.example.ppltracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void pullButton(View view){
        Intent pullIntent = new Intent(this, PullActivity.class);
        startActivity(pullIntent);
    }
    public void weightsButton(View view){
        Intent weightsIntent = new Intent(this, WeightsActivity.class);
        startActivity(weightsIntent);
    }
}
