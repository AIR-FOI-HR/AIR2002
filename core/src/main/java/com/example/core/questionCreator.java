package com.example.core;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.core.Fragments.MCAFragment;
import com.example.core.Fragments.SCAFragment;
import com.example.core.interfaces.DataLoaderListener;
import com.example.core.interfaces.QFragment;
import com.example.core.interfaces.QuizEnd;
import com.example.core.interfaces.callbackInterface;
import com.example.core.loader.WebServiceDataLoader;
import com.responses.QuestionsHandler.QuestionsListResponse;

import java.io.Serializable;
import java.util.List;

public class questionCreator implements DataLoaderListener, callbackInterface, Serializable {

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

    String gamePlay;

    private final Context context;
    private final QuizEnd End;

    public questionCreator(Context context, String[] questionList, String gamePlay, QuizEnd end) {
        this.context = context;
        this.End = end;
        this.questionList = new String[10];
        this.questionList = questionList;
        this.gamePlay=gamePlay;
    }


    @Override
    public void onDataLoaded(String status, String text, DataLoaderListener listener, List<QuestionsListResponse> questions, Integer points, String difficulty, String category, Integer kvizId) {
        this.listener = listener;
        this.difficulty = difficulty;
        this.category = category;
        this.kvizId = kvizId;
        Points = points;
        counter ++;

        if(kvizId == null) counter = 0;

        webServiceDataLoader = new WebServiceDataLoader(context);

        Activity activity = (Activity) context;
        fragmentTransaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();

        if(counter <= 9) {
            if (status.equals(Integer.toString(1))) {
                if (questions.size() == 1) {
                    question = questions.get(0);

                    QFragment fragment = null;

                    if (question.getQuestionTypeName().equals("MCQ")) {
                        fragment = new MCAFragment();
                    }
                    else if (question.getQuestionTypeName().equals("SCQ")) {
                        fragment = new SCAFragment();
                    }
                    else{
                        try {
                            throw new Exception("Called wrong question type!");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    QuestionData data = new QuestionData();
                    data.questionText = question.getQuestionText();
                    data.correctAnswers = question.getCorrectAnswer();
                    data.incorrectAnswers = question.getIncorrectAnswers();
                    data.points = Integer.toString(points);
                    if(gamePlay.equals("TimeTrial")) data.Flag = "StopWatch";


                    assert fragment != null;
                    fragmentTransaction.replace(com.example.core.R.id.questionFrameLayout, fragment.getFragment(data, this));
                    fragmentTransaction.commit();
                }
                if (questions.size() > 1) {
                    //ne koristimo
                }
            }
        }
        else {
            //multiplayer score
            int kvizTemp = (kvizId == null ? -1 : kvizId);
            End.setScoreData(Points, gamePlay, Integer.toString(kvizTemp), context);
        }
    }

    @Override
    public void onFinnish(boolean isCorrect, Integer pointsAdded) {
        if(gamePlay.equals("Multiplayer")){
            Points += pointsAdded;
            webServiceDataLoader.loadMpData(listener, Points, questionList[counter], kvizId);
        }
        else {
            if (isCorrect) {
                Points += pointsAdded;
                webServiceDataLoader.loadData(listener, Points, 1, difficulty, category, kvizId);
            } else {
                int kvizTemp = (kvizId == null ? -1 : kvizId);
                End.setScoreData(Points, gamePlay, Integer.toString(kvizTemp), context);
            }
        }
    }
}
