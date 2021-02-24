package com.example.trivialy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.core.interfaces.QuizEnd;
import com.example.core.loader.WebServiceDataLoader;
import com.example.core.questionCreator;

public class QuestionLoader implements QuizEnd {

    public void LoadQuestions(Context context, String gamePlay, String[] questionList, Integer kvizId){
        Load(context,null,null,gamePlay,questionList,kvizId);
    }

    public void LoadQuestions(Context context, String difficulty, String category, String gamePlay){
        Load(context,difficulty,category,gamePlay,null,null);
    }

    private void Load(Context context, String difficulty, String category, String gamePlay, String[] questionList, Integer kvizId){
        WebServiceDataLoader webServiceDataLoader = new WebServiceDataLoader(context);
        questionCreator listener = new questionCreator(context, questionList, gamePlay, this);

        if(gamePlay.equals("Multiplayer")){
            webServiceDataLoader.loadMpData(listener, 0, questionList[0], kvizId);
        }
        else{
            webServiceDataLoader.loadData(listener, 0, 1, difficulty, category, null);
        }
    }

    @Override
    public void setScoreData(Integer score, String quizType, String quizId, Context context) {
        Intent intent;
        if(quizType.equals("Multiplayer")){
            intent = new Intent(context, Leaderboard.class);
        }
        else{
            intent = new Intent(context, SingleplayerScore.class);
        }

        Bundle b = new Bundle();
        b.putInt("Score", score);
        b.putString("QuizType", quizType);
        b.putString("QuizId", quizId);
        intent.putExtras(b);

        context.startActivity(intent);
        Activity activity = (Activity) context;
        activity.finish();
    }
}
