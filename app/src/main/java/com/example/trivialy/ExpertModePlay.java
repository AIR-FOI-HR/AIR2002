package com.example.trivialy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.responses.GetDataService;
import com.responses.QuestionsHandler.QuestionsListResponse;
import com.responses.QuestionsHandler.QuestionRequest;
import com.responses.QuestionsHandler.QuestionsResponse;
import com.responses.RetrofitInstance;

import java.util.Random;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ExpertModePlay extends AppCompatActivity{

    TextView pointsField;
    TextView questionTextField;
    Button AnswerOne;
    Button AnswerTwo;
    Button AnswerThree;
    Button AnswerFour;

    private View.OnClickListener correctListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Integer pointsInteger = Integer.valueOf((String) pointsField.getText());
            pointsInteger += 1;
            pointsField.setText(pointsInteger.toString());

            GetExpertQuestions();
        }
    };

    private View.OnClickListener incorrectListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), SingleplayerMenu.class);
            ExpertModePlay.this.startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expert_mode_play);
        GetExpertQuestions();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), SingleplayerMenu.class);
        ExpertModePlay.this.startActivity(intent);
    }

    public void GetExpertQuestions(){
        pointsField = findViewById(R.id.pointsField);
        questionTextField = findViewById(R.id.quesrionTextField);
        AnswerOne = findViewById(R.id.answerOne);
        AnswerTwo = findViewById(R.id.answerTwo);
        AnswerThree = findViewById(R.id.answerThree);
        AnswerFour = findViewById(R.id.answerFour);

        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        QuestionRequest questionRequest = new QuestionRequest(1, "Hard", "Any Category");
        Call<QuestionsResponse> call = getDataService.GetQuestionsByCategoryAndDifficulty(questionRequest);
        call.enqueue(new Callback<QuestionsResponse>() {
            @Override
            public void onResponse(Response<QuestionsResponse> response, Retrofit retrofit) {
                if (!response.isSuccess()){
                    Toast t = Toast.makeText(getApplicationContext() , String.valueOf(response.code()), Toast.LENGTH_SHORT);
                    t.show();
                    return;
                }else{
                    if (response.body().getStatus().equals(Integer.toString(-1))){
                        Toast t = Toast.makeText(getApplicationContext() , response.body().getText(), Toast.LENGTH_SHORT);
                        t.show();
                    }else if(response.body().getStatus().equals(Integer.toString(-9)))
                    {
                        Toast t = Toast.makeText(getApplicationContext() , response.body().getText(), Toast.LENGTH_SHORT);
                        t.show();
                    }
                    else if(response.body().getStatus().equals(Integer.toString(1))){
                        QuestionsListResponse question = response.body().getQuestions().get(0);
                        questionTextField.setText(question.getQuestionText());

                        int random = new Random().nextInt(4) + 1;
                        String correctAnswer = question.getCorrectAnswer();
                        String[] wrongAnswers = question.getIncorrectAnswers().split(";");

                        if(random == 1){
                            AnswerOne.setText(correctAnswer);
                            AnswerOne.setOnClickListener(correctListener);

                            AnswerTwo.setText(wrongAnswers[0]);
                            AnswerTwo.setOnClickListener(incorrectListener);
                            if(wrongAnswers.length != 1){
                                AnswerOne.setVisibility(View.VISIBLE);
                                AnswerTwo.setVisibility(View.VISIBLE);
                                AnswerThree.setVisibility(View.VISIBLE);
                                AnswerFour.setVisibility(View.VISIBLE);

                                AnswerThree.setText(wrongAnswers[1]);
                                AnswerFour.setText(wrongAnswers[2]);

                                AnswerThree.setOnClickListener(incorrectListener);
                                AnswerFour.setOnClickListener(incorrectListener);
                            }
                            else if(wrongAnswers.length == 1){
                                AnswerThree.setVisibility(View.GONE);
                                AnswerFour.setVisibility(View.GONE);
                            }
                        }

                        if(random == 2){
                            AnswerTwo.setText(correctAnswer);
                            AnswerTwo.setOnClickListener(correctListener);

                            AnswerOne.setText(wrongAnswers[0]);
                            AnswerOne.setOnClickListener(incorrectListener);
                            if(wrongAnswers.length != 1){
                                AnswerOne.setVisibility(View.VISIBLE);
                                AnswerTwo.setVisibility(View.VISIBLE);
                                AnswerThree.setVisibility(View.VISIBLE);
                                AnswerFour.setVisibility(View.VISIBLE);

                                AnswerThree.setText(wrongAnswers[1]);
                                AnswerFour.setText(wrongAnswers[2]);

                                AnswerThree.setOnClickListener(incorrectListener);
                                AnswerFour.setOnClickListener(incorrectListener);
                            }
                            else if(wrongAnswers.length == 1){
                                AnswerThree.setVisibility(View.GONE);
                                AnswerFour.setVisibility(View.GONE);
                            }
                        }

                        if(random == 3){
                            AnswerThree.setText(correctAnswer);
                            AnswerThree.setOnClickListener(correctListener);

                            AnswerTwo.setText(wrongAnswers[0]);
                            AnswerTwo.setOnClickListener(incorrectListener);
                            if(wrongAnswers.length != 1){
                                AnswerOne.setVisibility(View.VISIBLE);
                                AnswerTwo.setVisibility(View.VISIBLE);
                                AnswerThree.setVisibility(View.VISIBLE);
                                AnswerFour.setVisibility(View.VISIBLE);

                                AnswerOne.setText(wrongAnswers[1]);
                                AnswerFour.setText(wrongAnswers[2]);

                                AnswerOne.setOnClickListener(incorrectListener);
                                AnswerFour.setOnClickListener(incorrectListener);
                            }
                            else if(wrongAnswers.length == 1){
                                AnswerOne.setVisibility(View.GONE);
                                AnswerFour.setVisibility(View.GONE);
                            }
                        }

                        if(random == 4){
                            AnswerFour.setText(correctAnswer);
                            AnswerFour.setOnClickListener(correctListener);

                            AnswerTwo.setText(wrongAnswers[0]);
                            AnswerTwo.setOnClickListener(incorrectListener);
                            if(wrongAnswers.length != 1){
                                AnswerOne.setVisibility(View.VISIBLE);
                                AnswerTwo.setVisibility(View.VISIBLE);
                                AnswerThree.setVisibility(View.VISIBLE);
                                AnswerFour.setVisibility(View.VISIBLE);

                                AnswerThree.setText(wrongAnswers[1]);
                                AnswerOne.setText(wrongAnswers[2]);

                                AnswerThree.setOnClickListener(incorrectListener);
                                AnswerOne.setOnClickListener(incorrectListener);
                            }
                            else if(wrongAnswers.length == 1){
                                AnswerThree.setVisibility(View.GONE);
                                AnswerOne.setVisibility(View.GONE);
                            }
                        }
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
