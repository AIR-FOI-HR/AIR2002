package com.example.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.core.Fragments.MCAFragment;
import com.example.core.Fragments.SCAFragment;
import com.example.core.loader.WebServiceDataLoader;
import com.responses.QuestionsHandler.QuestionsListResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class questionCreator implements DataLoaderListener, callbackInterface, Serializable {

    MCAFragment mcaFragment;
    SCAFragment scaFragment;
    QuestionsListResponse question;
    DataLoaderListener listener;
    WebServiceDataLoader webServiceDataLoader;
    FragmentTransaction fragmentTransaction;
    String difficulty;
    String category;
    String[] questionList;
    Integer kvizId;

    Integer counter = 0;
    Integer Points = 0;

    private Context context;
    Class score;

    public questionCreator(Context context, Class score, String[] questionList) {
        this.context = context;
        this.score = score;
        this.questionList = new String[10];
        this.questionList = questionList;
    }

    @Override
    public void onDataLoaded(String status, String text, DataLoaderListener listener, List<QuestionsListResponse> questions, Integer points, String difficulty, String category) {
        this.listener = listener;
        this.difficulty = difficulty;
        this.category = category;
        webServiceDataLoader = new WebServiceDataLoader(context);
        Activity activity = (Activity) context;
        fragmentTransaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
        Points = points;

        if (status.equals(Integer.toString(1))) {
            if (questions.size() == 1) {
                question = questions.get(0);

                if (question.getQuestionTypeName().equals("MCQ")) {
                    mcaFragment = new MCAFragment();
                }
                if (question.getQuestionTypeName().equals("SCQ")) {
                    scaFragment = new SCAFragment();
                }

                Bundle params = new Bundle();
                params.putString("Question", question.getQuestionText());
                params.putString("Correct", question.getCorrectAnswer());
                params.putString("Incorrect", question.getIncorrectAnswers());
                params.putString("Points", Integer.toString(points));

                if (question.getQuestionTypeName().equals("MCQ")) {
                    mcaFragment.setArguments(params);
                    mcaFragment.setCallback((callbackInterface) listener);
                    if (!mcaFragment.isVisible()) {
                        fragmentTransaction.replace(com.example.core.R.id.questionFrameLayout, mcaFragment);
                        fragmentTransaction.commit();
                    }
                }

                if (question.getQuestionTypeName().equals("SCQ")) {
                    scaFragment.setArguments(params);
                    scaFragment.setCallback((callbackInterface) listener);
                    fragmentTransaction.replace(com.example.core.R.id.questionFrameLayout, scaFragment);
                    fragmentTransaction.commit();
                }

            }
            if (questions.size() > 1) {
                //ne koristimo
            }
        }
    }

    @Override
    public void onMpDataLoaded(String status, String text, DataLoaderListener listener, List<QuestionsListResponse> questions, Integer points, Integer kvizId) {
        this.listener = listener;
        this.kvizId = kvizId;
        webServiceDataLoader = new WebServiceDataLoader(context);
        counter++;
        if (counter <= 10) {
            if (status.equals(Integer.toString(1))) {
                if (questions.size() == 1) {
                    question = questions.get(0);

                    Activity activity = (Activity) context;
                    fragmentTransaction =  ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction();

                    if (question.getQuestionTypeName().equals("MCQ")) {
                        mcaFragment = new MCAFragment();
                    }
                    if (question.getQuestionTypeName().equals("SCQ")) {
                        scaFragment = new SCAFragment();
                    }

                    Bundle params = new Bundle();
                    params.putString("Question", question.getQuestionText());
                    params.putString("Correct", question.getCorrectAnswer());
                    params.putString("Incorrect", question.getIncorrectAnswers());
                    params.putString("Points", Integer.toString(points));
                    params.putBoolean("IsMp", true);

                    if (question.getQuestionTypeName().equals("MCQ")) {
                        mcaFragment.setArguments(params);
                        mcaFragment.setCallback((callbackInterface) listener);
                        fragmentTransaction.replace(com.example.core.R.id.questionFrameLayout, mcaFragment);
                        fragmentTransaction.commit();
                    }

                    if (question.getQuestionTypeName().equals("SCQ")) {
                        scaFragment.setArguments(params);
                        scaFragment.setCallback((callbackInterface) listener);
                        fragmentTransaction.replace(com.example.core.R.id.questionFrameLayout, scaFragment);
                        fragmentTransaction.commit();
                    }

                }
                if (questions.size() > 1) {
                    //ne koristimo
                }
            }
        } else {
            Intent intent = new Intent(context, score);
            intent.putExtra("quizid", kvizId);
            context.startActivity(intent);
        }
    }

    @Override
    public void onMpFinnish(boolean isCorrect, Integer pointsAdded) {
        if (isCorrect) {
            Points += pointsAdded;
            webServiceDataLoader.loadMpData(listener, Points, questionList[counter], kvizId);
        } else {
            Intent intent = new Intent(context, score);
            intent.putExtra("quizid", kvizId);
            context.startActivity(intent);
        }
    }

    @Override
    public void onFinnish(boolean isCorrect, Integer pointsAdded) {
        if (isCorrect) {
            Points += pointsAdded;
            webServiceDataLoader.loadData(listener, Points, 1, difficulty, category);
        } else {
            Intent intent = new Intent(context, score);
            Bundle b = new Bundle();
            b.putString("Score", Points.toString());
            intent.putExtras(b);
            context.startActivity(intent);
            Activity activity = (Activity) context;
            activity.finish();
        }
    }
}
