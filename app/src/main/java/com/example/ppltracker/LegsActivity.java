package com.example.ppltracker;




import android.os.Bundle;
import android.view.View;
public class LegsActivity extends exerciseDay {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legs);

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
        exercises.add(new Exercise(getString(R.string.squatExercise), 5, prefs.getFloat("SQUAT_KG", 50f),"SQUAT_KG"));
        exercises.add(new Exercise(getString(R.string.romanianExercise), 3, prefs.getFloat("RDL_KG", 30f),"RDL_KG"));
        exercises.add(new Exercise(getString(R.string.legpressExercise), 3, prefs.getFloat("legP_KG",80f),"legP_KG"));
        exercises.add(new Exercise(getString(R.string.hamcurlExercise), 3, prefs.getFloat("hamcurl_KG",25f), "ham_KG"));
        exercises.add(new Exercise(getString(R.string.calfExercise), 3, prefs.getFloat("calf_KG",80f), "calf_KG"));
        exercises.add(new Exercise(getString(R.string.hanglegExercise),3, 0f,"hang_KG"));
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
