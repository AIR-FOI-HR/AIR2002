package com.example.trivialy.Gamemodes;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.core.Counter;
import com.example.core.QuestionLoader;
import com.example.core.questionCreator;
import com.example.core.loader.WebServiceDataLoader;
import com.example.trivialy.SingleplayerScore;

public class FreePlay extends AppCompatActivity {

    Intent i;
    String category;
    String difficulty;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.core.R.layout.question_frame);
        i = getIntent();
        category = (String) i.getSerializableExtra("category");
        difficulty = (String) i.getSerializableExtra("difficulty");
        Counter.setCounter(0);
        initializeUi();

    }


    private void initializeUi() {
        QuestionLoader.LoadQuestions(this, difficulty, category, SingleplayerScore.class);
    }


}
