package com.example.trivialy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.responses.GetDataService;
import com.responses.QuestionsHandler.QuestionListResponse;
import com.responses.QuestionsHandler.QuestionRequest;
import com.responses.QuestionsHandler.QuestionsResponse;
import com.responses.RetrofitInstance;

import java.io.Serializable;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ExpertModePlay extends AppCompatActivity{

    TextView questionTextField;
    Button AnswerOne;
    Button AnswerTwo;
    Button AnswerThree;
    Button AnswerFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expert_mode_play);
        GetExpertQuestions();
    }

    public void GetExpertQuestions(){
        questionTextField = findViewById(R.id.quesrionTextField);
        AnswerOne = findViewById(R.id.answerOne);
        AnswerTwo = findViewById(R.id.answerTwo);
        AnswerThree = findViewById(R.id.answerThree);
        AnswerFour = findViewById(R.id.answerFour);

        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        QuestionRequest questionRequest = new QuestionRequest(1, "Hard", "Any Category");
        Call<QuestionsResponse> call = getDataService.getQuestionsByCategoryAndDifficulty(questionRequest);
        call.enqueue(new Callback<QuestionsResponse>() {
            @Override
            public void onResponse(Response<QuestionsResponse> response, Retrofit retrofit) {
                if (!response.isSuccess()){
                    Toast t = Toast.makeText(getApplicationContext() , String.valueOf(response.code()), Toast.LENGTH_SHORT);
                    t.show();
                    return;
                }else{
                    if (response.body().getStatus() == Integer.toString(-1)){
                        Toast t = Toast.makeText(getApplicationContext() , response.body().getText(), Toast.LENGTH_SHORT);
                        t.show();
                    }else if(response.body().getStatus() == Integer.toString(-9))
                    {
                        Toast t = Toast.makeText(getApplicationContext() , response.body().getText(), Toast.LENGTH_SHORT);
                        t.show();
                    }
                    else if(response.body().getStatus() == Integer.toString(1)){
                        QuestionListResponse question = response.body().getQuestions().get(0);
                        questionTextField.setText(question.getQuestionText());
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast t1 = Toast.makeText(getApplicationContext() , "There was an error while loading questions!\n" + t.getMessage(), Toast.LENGTH_SHORT);
                t1.show();
            }
        });
    }
}
