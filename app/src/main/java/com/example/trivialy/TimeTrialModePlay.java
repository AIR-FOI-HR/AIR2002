package com.example.trivialy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.responses.GetDataService;
import com.responses.QuestionsHandler.QuestionRequest;
import com.responses.QuestionsHandler.QuestionsListResponse;
import com.responses.QuestionsHandler.QuestionsResponse;
import com.responses.RetrofitInstance;

import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class TimeTrialModePlay extends AppCompatActivity {

    Integer Points = 0;
    TextView pointsField;
    TextView questionTextField;
    Button AnswerOne;
    Button AnswerTwo;
    Button AnswerThree;
    Button AnswerFour;

    ConstraintLayout layoutExp;
    TextView textView;
    private int seconds = 0;
    private boolean running;
    String prikaz;


    CountDownTimer timer2 = new CountDownTimer(30000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            Intent intent = new Intent(getApplicationContext(), SingleplayerScore.class);
            Bundle b = new Bundle();
            b.putString("Score", Points.toString());
            intent.putExtras(b);
            TimeTrialModePlay.this.startActivity(intent);
            finish();
        }
    };

    private View.OnClickListener correctListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Points += 1;
            pointsField.setText(Points.toString());
            Intent i = getIntent();
            String category = (String) i.getSerializableExtra("category");
            String difficulty = (String) i.getSerializableExtra("difficulty");
            GetExpertQuestions(difficulty, category);
            timer2.cancel();
            timer2.start();
            seconds=0;
        }
    };
    private View.OnClickListener incorrectListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            timer2.cancel();
            Intent intent = new Intent(getApplicationContext(), SingleplayerScore.class);
            Bundle b = new Bundle();
            b.putString("Score", Points.toString());
            intent.putExtras(b);
            TimeTrialModePlay.this.startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expert_mode_play);
        Intent i = getIntent();
        String category = (String) i.getSerializableExtra("category");
        String difficulty = (String) i.getSerializableExtra("difficulty");
        GetExpertQuestions(difficulty, category);


        layoutExp = findViewById(R.id.expertLayout);
        textView = new TextView(this);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(33);

        running=true;
        runTimer();

        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(10,21,0,0);
        textView.setLayoutParams(params);
        layoutExp.addView(textView);

        timer2.start();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), SingleplayerMenu.class);
        TimeTrialModePlay.this.startActivity(intent);
        finish();
    }

    public void GetExpertQuestions(String difficulty, String category) {
        pointsField = findViewById(R.id.pointsField);
        questionTextField = findViewById(R.id.quesrionTextField);
        AnswerOne = findViewById(R.id.answerOne);
        AnswerTwo = findViewById(R.id.answerTwo);
        AnswerThree = findViewById(R.id.answerThree);
        AnswerFour = findViewById(R.id.answerFour);

        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        QuestionRequest questionRequest = new QuestionRequest(1, difficulty, category);
        Call<QuestionsResponse> call = getDataService.GetQuestionsByCategoryAndDifficulty(questionRequest);
        call.enqueue(new Callback<QuestionsResponse>() {
            @Override
            public void onResponse(Response<QuestionsResponse> response, Retrofit retrofit) {
                if (!response.isSuccess()) {
                    Toast t = Toast.makeText(getApplicationContext(), String.valueOf(response.code()), Toast.LENGTH_SHORT);
                    t.show();
                    return;
                } else {
                    if (response.body().getStatus().equals(Integer.toString(-1))) {
                        Toast t = Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_SHORT);
                        t.show();
                    } else if (response.body().getStatus().equals(Integer.toString(-9))) {
                        Toast t = Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_SHORT);
                        t.show();
                    } else if (response.body().getStatus().equals(Integer.toString(1))) {
                        QuestionsListResponse question = response.body().getQuestions().get(0);
                        questionTextField.setText(question.getQuestionText());

                        int random = new Random().nextInt(4) + 1;
                        String correctAnswer = question.getCorrectAnswer();
                        String[] wrongAnswers = question.getIncorrectAnswers().split(";");

                        if (random == 1) {
                            AnswerOne.setText(correctAnswer);
                            AnswerOne.setOnClickListener(correctListener);

                            AnswerTwo.setText(wrongAnswers[0]);
                            AnswerTwo.setOnClickListener(incorrectListener);

                            AnswerOne.setVisibility(View.VISIBLE);
                            AnswerTwo.setVisibility(View.VISIBLE);
                            AnswerThree.setVisibility(View.VISIBLE);
                            AnswerFour.setVisibility(View.VISIBLE);


                            if (wrongAnswers.length != 1) {
                                AnswerThree.setText(wrongAnswers[1]);
                                AnswerFour.setText(wrongAnswers[2]);

                                AnswerThree.setOnClickListener(incorrectListener);
                                AnswerFour.setOnClickListener(incorrectListener);
                            } else if (wrongAnswers.length == 1) {
                                AnswerThree.setVisibility(View.GONE);
                                AnswerFour.setVisibility(View.GONE);
                            }
                        }

                        if (random == 2) {
                            AnswerTwo.setText(correctAnswer);
                            AnswerTwo.setOnClickListener(correctListener);

                            AnswerOne.setText(wrongAnswers[0]);
                            AnswerOne.setOnClickListener(incorrectListener);

                            AnswerOne.setVisibility(View.VISIBLE);
                            AnswerTwo.setVisibility(View.VISIBLE);
                            AnswerThree.setVisibility(View.VISIBLE);
                            AnswerFour.setVisibility(View.VISIBLE);

                            if (wrongAnswers.length != 1) {
                                AnswerThree.setText(wrongAnswers[1]);
                                AnswerFour.setText(wrongAnswers[2]);

                                AnswerThree.setOnClickListener(incorrectListener);
                                AnswerFour.setOnClickListener(incorrectListener);
                            } else if (wrongAnswers.length == 1) {
                                AnswerThree.setVisibility(View.GONE);
                                AnswerFour.setVisibility(View.GONE);
                            }
                        }

                        if (random == 3) {
                            AnswerThree.setText(correctAnswer);
                            AnswerThree.setOnClickListener(correctListener);

                            AnswerTwo.setText(wrongAnswers[0]);
                            AnswerTwo.setOnClickListener(incorrectListener);

                            AnswerOne.setVisibility(View.VISIBLE);
                            AnswerTwo.setVisibility(View.VISIBLE);
                            AnswerThree.setVisibility(View.VISIBLE);
                            AnswerFour.setVisibility(View.VISIBLE);

                            if (wrongAnswers.length != 1) {
                                AnswerOne.setText(wrongAnswers[1]);
                                AnswerFour.setText(wrongAnswers[2]);

                                AnswerOne.setOnClickListener(incorrectListener);
                                AnswerFour.setOnClickListener(incorrectListener);
                            } else if (wrongAnswers.length == 1) {
                                AnswerOne.setVisibility(View.GONE);
                                AnswerFour.setVisibility(View.GONE);
                            }
                        }

                        if (random == 4) {
                            AnswerFour.setText(correctAnswer);
                            AnswerFour.setOnClickListener(correctListener);

                            AnswerTwo.setText(wrongAnswers[0]);
                            AnswerTwo.setOnClickListener(incorrectListener);

                            AnswerOne.setVisibility(View.VISIBLE);
                            AnswerTwo.setVisibility(View.VISIBLE);
                            AnswerThree.setVisibility(View.VISIBLE);
                            AnswerFour.setVisibility(View.VISIBLE);

                            if (wrongAnswers.length != 1) {
                                AnswerThree.setText(wrongAnswers[1]);
                                AnswerOne.setText(wrongAnswers[2]);

                                AnswerThree.setOnClickListener(incorrectListener);
                                AnswerOne.setOnClickListener(incorrectListener);
                            } else if (wrongAnswers.length == 1) {
                                AnswerThree.setVisibility(View.GONE);
                                AnswerOne.setVisibility(View.GONE);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast t1 = Toast.makeText(getApplicationContext(), "There was an error while loading questions!\n" + t.getMessage(), Toast.LENGTH_SHORT);
                t1.show();
            }
        });
    }

    private void runTimer()
    {
        final Handler handler
                = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run()
            {
                if (running) {
                    seconds++;
                }
                prikaz = Integer.toString(seconds);
                textView.setText(prikaz);
                handler.postDelayed(this, 1000);
            }
        });
    }
}
