package com.example.trivialy;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.core.Counter;
import com.example.core.DataLoaderListener;
import com.example.core.Fragments.MCAFragment;
import com.example.core.Fragments.SCAFragment;
import com.example.core.callbackInterface;
import com.example.trivialy.loader.MultiplayerDataLoader;
import com.responses.QuestionsHandler.QuestionsListResponse;

import java.util.List;

public class MultiplayerPlay extends AppCompatActivity implements DataLoaderListener, callbackInterface {

    String kvizIds;
    Integer kvizId;
    String[] questionList;
    MultiplayerDataLoader mpLoader;
    Integer counter;
    QuestionsListResponse question;
    Integer Points = 0;

    private FragmentTransaction fragmentTransaction;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.core.R.layout.question_frame);

        counter = 0;

        mpLoader = new MultiplayerDataLoader();

        kvizIds = getIntent().getStringExtra("kvizIds");
        kvizId = getIntent().getIntExtra("kvizId", 0);

        questionList = kvizIds.split(";");
        mpLoader.LoadData(this, 0, questionList[0]);

        Counter.setCounter(0);
    }

    @Override
    public void onDataLoaded(String status, String text, List<QuestionsListResponse> questions, Integer points) {
        counter++;
        if(counter <= 10){
            if(status.equals(Integer.toString(1))) {
                if (questions.size() == 1) {
                    question = questions.get(0);

                    if (question.getQuestionTypeName().equals("MCQ")) {
                        MCAFragment mcaFragment = new MCAFragment();

                        Bundle params = new Bundle();
                        params.putString("Question", question.getQuestionText());
                        params.putString("Correct", question.getCorrectAnswer());
                        params.putString("Incorrect", question.getIncorrectAnswers());
                        params.putString("Points", Integer.toString(points));
                        mcaFragment.setArguments(params);

                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(com.example.core.R.id.questionFrameLayout, mcaFragment);
                        fragmentTransaction.commit();
                    }
                    if (question.getQuestionTypeName().equals("SCQ")) {
                        SCAFragment scaFragment = new SCAFragment();

                        Bundle params = new Bundle();
                        params.putString("Question", question.getQuestionText());
                        params.putString("Correct", question.getCorrectAnswer());
                        params.putString("Incorrect", question.getIncorrectAnswers());
                        params.putString("Points", Integer.toString(points));
                        scaFragment.setArguments(params);

                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(com.example.core.R.id.questionFrameLayout, scaFragment);
                        fragmentTransaction.commit();
                    }
                }
                if (questions.size() > 1) {
                    //ne koristimo
                }
            }
        }
        else{
            Intent intent = new Intent(getApplicationContext(), Leaderboard.class);
            intent.putExtra("quizid",kvizId);
            MultiplayerPlay.this.startActivity(intent);
        }
    }

    @Override
    public void onFinnish(boolean isCorrect, Integer pointsAdded) {
        if(isCorrect){
            Points += pointsAdded;
            mpLoader.LoadData(this, Points, questionList[counter]);
        }
        else{
            Intent intent = new Intent(getApplicationContext(), Leaderboard.class);
            intent.putExtra("quizid",kvizId);
            MultiplayerPlay.this.startActivity(intent);
        }
    }
}
