package com.example.ppltracker;



import android.os.Bundle;
import android.view.View;


public class PullActivity extends exerciseDay {
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
    //creates the arraylist of exercises by fetching strings and weights from strings.xml/sharedpreferences
    @Override
    public void constructExercises(){
        //Adds to and checks the pull-variety counter to decide exercises
        editor.putInt("Pull_day", (prefs.getInt("Pull_day", 0) +1));
        editor.commit();
        //mod 0 = DL, else = BBR
        if (prefs.getInt("Pull_day", 0) % 2 == 0){
            exercises.add(new Exercise(getString(R.string.dlExercise), 3, prefs.getFloat("DL_KG", 70f),"DL_KG"));
        }
        else {
            exercises.add(new Exercise(getString(R.string.bbrExercise), 5, prefs.getFloat("BBR_KG", 30f),"BBR_KG"));
        }
        exercises.add(new Exercise(getString(R.string.pullupExercise), 3, prefs.getFloat("pullUp_KG",0.0f),"pullUp_KG"));
        exercises.add(new Exercise(getString(R.string.cableRowExercise), 3, prefs.getFloat("cableRow_KG",45.0f), "cableRow_KG"));
        exercises.add(new Exercise(getString(R.string.faceExercise), 5, prefs.getFloat("face_KG",15f), "face_KG"));
        exercises.add(new Exercise(getString(R.string.curlExercise),4, prefs.getFloat("curl_KG",8f),"curl_KG"));
    }
    @Override
    public void toggleTimer(View view){
        if (timerOn){
            stopTimer();
        }
        else{
            startTimer();
        }
    }

}
