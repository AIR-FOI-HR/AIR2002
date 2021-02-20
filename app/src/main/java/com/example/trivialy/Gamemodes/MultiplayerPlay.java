package com.example.trivialy.Gamemodes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.core.Counter;
import com.example.core.QuestionLoader;
import com.example.core.loader.WebServiceDataLoader;
import com.example.core.questionCreator;
import com.example.core.loader.MultiplayerDataLoader;
import com.example.trivialy.Leaderboard;
import com.example.trivialy.SingleplayerScore;
import com.responses.QuestionsHandler.Question;
import com.responses.QuestionsHandler.QuestionsListResponse;

public class MultiplayerPlay extends AppCompatActivity {

    String kvizIds;
    Integer kvizId;
    String[] questionList;
    Integer counter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.core.R.layout.question_frame);
        counter = 0;

        kvizIds = getIntent().getStringExtra("kvizIds");
        kvizId = getIntent().getIntExtra("kvizId", 0);
        questionList = kvizIds.split(";");

        QuestionLoader.LoadMpQuestions(this, Leaderboard.class, questionList, kvizId);

        Counter.setCounter(0);
    }
}
