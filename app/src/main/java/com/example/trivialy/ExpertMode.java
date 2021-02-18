package com.example.trivialy;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.core.DataLoaderListener;
import com.example.core.callbackInterface;
import com.example.core.Fragments.MCAFragment;
import com.example.core.Fragments.SCAFragment;
import com.example.trivialy.loader.WebServiceDataLoader;
import com.responses.QuestionsHandler.QuestionsListResponse;

import java.util.List;

public class ExpertMode extends AppCompatActivity implements DataLoaderListener, callbackInterface {
    private WebServiceDataLoader webServiceDataLoader;
    private QuestionsListResponse question;
    private FragmentTransaction fragmentTransaction;

    Integer Points = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.core.R.layout.question_frame);
        initializeUi();

    }

    private void initializeUi() {
        webServiceDataLoader = new WebServiceDataLoader(this);

        webServiceDataLoader.loadData(this, Points, 1, "Hard", "Any Category");
    }

    @Override
    public void onDataLoaded(String status, String text, List<QuestionsListResponse> questions, Integer points) {
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

<<<<<<< HEAD


=======
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
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

    @Override
    public void onFinnish(boolean isCorrect, Integer pointsAdded) {
        if(isCorrect){
            Points += pointsAdded;
            webServiceDataLoader.loadData(this, Points, 1, "Hard", "Any Category");
        }
        else{
            Intent intent = new Intent(this, SingleplayerScore.class);
            Bundle b = new Bundle();
            b.putString("Score", Points.toString());
            intent.putExtras(b);
            ExpertMode.this.startActivity(intent);
            finish();
        }
    }
}
