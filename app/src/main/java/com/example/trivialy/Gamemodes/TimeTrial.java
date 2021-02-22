package com.example.trivialy.Gamemodes;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

import com.example.core.Counter;
import com.example.core.QuestionLoader;
import com.example.trivialy.SingleplayerScore;

public class TimeTrial extends AppCompatActivity{

    Integer Points = 0;

    private int seconds = 0;
    private boolean running;

    Intent i;
    String category;
    String difficulty;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.core.R.layout.question_frame);
        i = getIntent();
        category = (String) i.getSerializableExtra("category");
        difficulty = (String) i.getSerializableExtra("difficulty");

        initializeUi();
        Counter.setCounter(0);


    }

    private void initializeUi() {
        QuestionLoader.LoadTimeTrialPlayQuestions(this, difficulty, category, SingleplayerScore.class);
    }

    @Override
    public void onBackPressed() {

    }
}
