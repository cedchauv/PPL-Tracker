package com.example.ppltracker;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PullActivity extends AppCompatActivity {
    private TextView countdownText;
    private Button countdownButton;
    private MediaPlayer mp;

    private CountDownTimer countdownTimer;
    private long msLeft = 1000;
    private boolean timerOn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);

        countdownText = findViewById(R.id.countdownText);
        countdownButton = findViewById(R.id.restButton);
        mp = MediaPlayer.create(this, Settings.System.DEFAULT_NOTIFICATION_URI);
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
            }
        }.start();
    }
    private void stopTimer(){
        timerOn = false;
        countdownTimer.cancel();

    }
}
