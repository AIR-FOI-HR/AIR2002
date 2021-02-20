package com.example.trivialy.Gamemodes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.core.Counter;
import com.example.core.QuestionLoader;
import com.example.core.questionCreator;
import com.example.core.loader.WebServiceDataLoader;
import com.example.trivialy.SingleplayerScore;

public class ExpertMode extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.core.R.layout.question_frame);
        initializeUi();
        Counter.setCounter(0);
    }

    private void initializeUi() {
        String difficulty = "Hard";
        String category = "Any Category";

        QuestionLoader.LoadQuestions(this, difficulty, category, SingleplayerScore.class);
    }
}
