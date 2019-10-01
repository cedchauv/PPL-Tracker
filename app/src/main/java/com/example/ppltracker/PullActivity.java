package com.example.ppltracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PullActivity extends AppCompatActivity {
    private TextView countdownText;
    private MediaPlayer mp;

    private TextView currentSetText;
    private TextView maxSetText;
    private TextView exerciseText;
    private TextView exerciseKg;
    private int currentSet = 1;
    private int maxSet;
    private int count = 0;
    private ArrayList<Exercise> exercises = new ArrayList<>();

    private CountDownTimer countdownTimer;
    private long msLeft = 18000;
    private boolean timerOn;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);

        countdownText = findViewById(R.id.countdownText);
        currentSetText = findViewById(R.id.currentSet);
        maxSetText = findViewById(R.id.maxSet);
        exerciseKg = findViewById(R.id.exerciseKG);
        exerciseText = findViewById((R.id.exerciseText));
        mp = MediaPlayer.create(this, Settings.System.DEFAULT_NOTIFICATION_URI);

        prefs = getSharedPreferences("PPL_prefs.xml", Context.MODE_PRIVATE);
        editor = prefs.edit();
        editor.putInt("Pull_day", (prefs.getInt("Pull_day", 0) +1));
        editor.commit();

        constructExercises();
        setExerciseTexts();

    }
    //creates the arraylist of exercises by fetching strings and weights from strings.xml/sharedpreferences
    public void constructExercises(){
        //mod 0 = DL, else = BBR
        if (prefs.getInt("Pull_day", 0) % 2 == 0){
            exercises.add(new Exercise(getString(R.string.dlExercise), 3, prefs.getFloat("DL_KG", 70f)));
        }
        else {
            exercises.add(new Exercise(getString(R.string.bbrExercise), 5, prefs.getFloat("BBR_KG", 30f)));
        }
        //Add if for pull varaint (BBR/DL) - need sharedpreference
        exercises.add(new Exercise(getString(R.string.dlExercise), 3, prefs.getFloat("DL_KG", 70f))); //replace 50 with sharedpreferences get thingy
        exercises.add(new Exercise(getString(R.string.pullupExercise), 3, 0.0f)); //replace 0 with sharedpreferences get thingy
        exercises.add(new Exercise(getString(R.string.cableRowExercise), 3, 45.0f));

    }
    public void toggleTimer(View view){
        if (timerOn){
            stopTimer();
        }
        else{
            startTimer();
        }
    }
    private void startTimer(){
        timerOn = true;
        countdownTimer = new CountDownTimer(msLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countdownText.setText("" +millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                mp.start();
                timerOn=false;
                if (count < 1){
                    countdownText.setText("180");
                }
                else{
                    countdownText.setText("90");
                    msLeft = 9000;
                }
                if (currentSet == maxSet){
                    currentSet = 1;
                    count++;
                    setExerciseTexts();
                }
                else {
                    currentSet++;
                    currentSetText.setText(Integer.toString(currentSet));
                }
            }
        }.start();
    }
    private void stopTimer(){
        timerOn = false;
        countdownTimer.cancel();

    }
    private void setExerciseTexts(){
        maxSet =  exercises.get(count).getMaxSet();
        maxSetText.setText(Integer.toString(maxSet));
        currentSetText.setText(Integer.toString(currentSet));
        exerciseText.setText(exercises.get(count).getName());
        exerciseKg.setText("" + exercises.get(count).getWeight());
    }
}
