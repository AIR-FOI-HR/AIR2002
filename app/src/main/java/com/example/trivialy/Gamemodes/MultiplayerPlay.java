package com.example.trivialy.Gamemodes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.core.Counter;
import com.example.trivialy.QuestionLoader;
import com.example.trivialy.Leaderboard;

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

        QuestionLoader questionLoader = new QuestionLoader();
        questionLoader.LoadQuestions(this, "Multiplayer", questionList, kvizId);

        Counter.setCounter(0);
    }
}
