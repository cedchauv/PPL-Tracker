package com.example.ppltracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public abstract class exerciseDay extends AppCompatActivity {

    protected TextView countdownText;
    protected MediaPlayer mp;

    protected TextView currentSetText;
    protected TextView maxSetText;
    protected TextView exerciseText;
    protected TextView exerciseKg;
    protected int currentSet = 1;
    protected int maxSet;
    protected int count = 0;
    protected ArrayList<Exercise> exercises = new ArrayList<>();

    protected CountDownTimer countdownTimer;
    protected long msLeft = 18000;
    protected boolean timerOn;

    protected SharedPreferences prefs;
    protected SharedPreferences.Editor editor;
    protected AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mp = MediaPlayer.create(this, Settings.System.DEFAULT_NOTIFICATION_URI);
        prefs = getSharedPreferences("PPL_prefs.xml", Context.MODE_PRIVATE);
        editor = prefs.edit();

        //AlertDialog for adding weight to exercise
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Completed Exercise!");
        builder.setMessage("Do you want to add weight to exercise?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Adds 2.5kg to current exercise and closes the dialog
                editor.putFloat(exercises.get(count).getKey(), (prefs.getFloat(exercises.get(count).getKey(), 10f) + 2.5f));
                editor.commit();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Does nothing but close the dialog
                dialog.dismiss();
            }
        });
        alert = builder.create();

         /* ADD BELOW TO SUBCLASS METHOD!
         ----------------------------------
        countdownText = findViewById(R.id.countdownText);
        currentSetText = findViewById(R.id.currentSet);
        maxSetText = findViewById(R.id.maxSet);
        exerciseKg = findViewById(R.id.exerciseKG);
        exerciseText = findViewById((R.id.exerciseText));
        constructExercises();
        setExerciseTexts();*/
    }
    protected abstract void constructExercises();

    protected abstract void toggleTimer(View View);
    protected void startTimer(){
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
                if (count < 2){
                    countdownText.setText("180");
                }
                else{
                    countdownText.setText("90");
                    msLeft = 9000;
                }
                if (currentSet == maxSet){
                    currentSet = 1;
                    alert.show();
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
    protected void stopTimer(){
        timerOn = false;
        countdownTimer.cancel();
    }

    protected void setExerciseTexts(){
        maxSet = exercises.get(count).getMaxSet();
        maxSetText.setText(Integer.toString(maxSet));
        currentSetText.setText(Integer.toString(currentSet));
        exerciseText.setText(exercises.get(count).getName());
        exerciseKg.setText("" + exercises.get(count).getWeight());
    }
}
