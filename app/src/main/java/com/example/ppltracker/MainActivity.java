package com.example.ppltracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("PPL_prefs.xml", Context.MODE_PRIVATE);
        editor = prefs.edit();

        TextView scheduleText = findViewById(R.id.schedule);

        switch(prefs.getString("Scheduled_day", "Pull")){
            case "Legs":
                scheduleText.setText("LEGS");
                break;
            case "Push":
                scheduleText.setText("PUSH");
                break;
            case "Pull":
                scheduleText.setText("PULL");
                break;
            default:
                scheduleText.setText("wack");
                break;
        }
    }
    public void pullButton(View view){
        Intent pullIntent = new Intent(this, PullActivity.class);
        scheduleDay("Push");
        startActivity(pullIntent);
    }
    public void pushButton(View view){
        Intent pushIntent = new Intent(this, PushActivity.class);
        scheduleDay("Legs");
        startActivity(pushIntent);
    }
    public void legsButton(View view){
        Intent legsIntent = new Intent(this, LegsActivity.class);
        scheduleDay("Pull");
        startActivity(legsIntent);
    }
    public void weightsButton(View view){
        Intent weightsIntent = new Intent(this, WeightSettings.class);
        startActivity(weightsIntent);
    }
    private void scheduleDay(String day){
        editor.putString("Scheduled_day", day);
        editor.apply();
    }
}
