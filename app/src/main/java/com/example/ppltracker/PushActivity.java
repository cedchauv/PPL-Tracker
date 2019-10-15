package com.example.ppltracker;

import android.os.Bundle;
import android.view.View;

public class PushActivity extends exerciseDay {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);

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
            exercises.add(new Exercise(getString(R.string.BenchHExercise), 5, prefs.getFloat("BenchH_KG", 50f),"BenchH_KG"));
            exercises.add(new Exercise(getString(R.string.ohpLExercise), 3, prefs.getFloat("ohpL_KG",30f),"ohpL_KG"));
        }
        else {
            exercises.add(new Exercise(getString(R.string.ohpHExercise), 5, prefs.getFloat("ohpH_KG",40f),"ohpH_KG"));
            exercises.add(new Exercise(getString(R.string.benchLExercise), 5, prefs.getFloat("BenchL_KG", 30f),"BenchL_KG"));
        }
        exercises.add(new Exercise(getString(R.string.inclineExercise), 3, prefs.getFloat("incline_KG",14f), "incline_KG"));
        exercises.add(new Exercise(getString(R.string.dipsExercise), 3, prefs.getFloat("dips_KG",0f), "dips_KG"));
        exercises.add(new Exercise(getString(R.string.pushdownExercise),3, prefs.getFloat("pushdown_KG",8f),"pushdown_KG"));
        exercises.add(new Exercise(getString(R.string.suitcaseExercise), 3, prefs.getFloat("suitcase_KG",22f),"suitcase_KG"));


    }

    @Override
    protected void toggleTimer(View View) {
        if (timerOn){
            stopTimer();
        }
        else{
            startTimer();
        }
    }
}
