package com.example.ppltracker;

import android.os.Bundle;
import android.view.View;

public class PushActivity extends exerciseDay {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);

        countdownText = findViewById(R.id.countdownText);
        currentSetText = findViewById(R.id.currentSet);
        maxSetText = findViewById(R.id.maxSet);
        exerciseKg = findViewById(R.id.exerciseKG);
        exerciseText = findViewById((R.id.exerciseText));

        constructExercises();
        setExerciseTexts();
    }
    @Override
    protected void constructExercises() {
        //Adds to and checks the push-variety counter to decide exercises
        editor.putInt("Push_day", (prefs.getInt("Push_day", 0) +1));
        editor.commit();
        //mod 0 = Bench, else = OHP
        if (prefs.getInt("Push_day", 0) % 2 == 0){
            exercises.add(new Exercise(getString(R.string.BenchHExercise), 5, prefs.getFloat("HB_KG", 50f),"HB_KG"));
            exercises.add(new Exercise(getString(R.string.ohpLExercise), 3, prefs.getFloat("lohp_KG",30f),"lohp_KG"));
        }
        else {
            exercises.add(new Exercise(getString(R.string.ohpHExercise), 5, prefs.getFloat("hohp_KG",40f),"hohp_KG"));
            exercises.add(new Exercise(getString(R.string.benchLExercise), 3, prefs.getFloat("LB_KG", 30f),"LB_KG"));
        }
        exercises.add(new Exercise(getString(R.string.inclineExercise), 3, prefs.getFloat("inc_KG",14f), "inc_KG"));
        exercises.add(new Exercise(getString(R.string.dipsExercise), 3, prefs.getFloat("dips_KG",0f), "dips_KG"));
        exercises.add(new Exercise(getString(R.string.pushdownExercise),3, prefs.getFloat("pushdown_KG",8f),"pushdown_KG"));
        exercises.add(new Exercise(getString(R.string.suitcaseExercise), 2, prefs.getFloat("suitcase_KG",22f),"suitcase_KG"));


    }
    @Override
    public void toggleTimer(View view) {
        if (timerOn){
            stopTimer();
        }
        else{
            startTimer();
        }
    }
}
