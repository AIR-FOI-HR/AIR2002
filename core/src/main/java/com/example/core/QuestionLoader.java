package com.example.core;

import android.content.Context;

import com.example.core.loader.WebServiceDataLoader;

public class QuestionLoader {

    public static void LoadQuestions(Context context, String difficulty, String category, Class score){
        WebServiceDataLoader webServiceDataLoader = new WebServiceDataLoader(context);
        questionCreator listener = new questionCreator(context, score, null);

        webServiceDataLoader.loadData(listener, 0, 1, difficulty, category);
    }

    public static void LoadTimeTrialPlayQuestions(Context context, String difficulty, String category, Class score){
        WebServiceDataLoader webServiceDataLoader = new WebServiceDataLoader(context);
        questionCreator listener = new questionCreator(context, score, null,"TimeTrial");
        webServiceDataLoader.loadData(listener, 0, 1, difficulty, category);
    }

    public static void LoadMpQuestions(Context context, Class leaderboard, String[] questionList, Integer kvizId){
        WebServiceDataLoader webServiceDataLoader = new WebServiceDataLoader(context);
        questionCreator listener = new questionCreator(context, leaderboard, questionList);

        webServiceDataLoader.loadMpData(listener, 0, questionList[0], kvizId);
    }
}
