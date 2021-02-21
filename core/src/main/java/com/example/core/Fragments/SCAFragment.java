package com.example.core.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.core.Counter;
import com.example.core.PowerUps;
import com.example.core.R;
import com.example.core.callbackInterface;

import java.util.Random;

public class SCAFragment extends Fragment {

    TextView pointsField;
    TextView questionTextField;
    Button AnswerOne;
    Button AnswerTwo;
    Button AnswerThree;
    Button AnswerFour;

    String questionText;
    String correctAnswer;
    String incorrectAnswers;

    int n = Counter.counter;
    int counterCorrect = Counter.counterCorrect;
    Button bomb;
    Button halfButton;

    String points;

    TextView stopWatch;
    int seconds = 0;
    String prikaz;
    String flag = "Da";

    Boolean isMp;

    CountDownTimer timer2 = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            seconds++;
            prikaz = Integer.toString(seconds);
            stopWatch.setText(prikaz);
        }

        @Override
        public void onFinish() {
        }
    };

    public SCAFragment() {}

    callbackInterface callback;

    public void setCallback(callbackInterface callback){
        this.callback = callback;
    }

    private View.OnClickListener correctListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(isMp){
                callback.onMpFinnish(true, 1);
            }
            else{
                callback.onFinnish(true, 1);
            }
            counterCorrect++;
            Counter.setCounterCorrect(counterCorrect);
        }
    };

    private View.OnClickListener incorrectListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(isMp){
                callback.onMpFinnish(false, 0);
            }
            else {
                callback.onFinnish(false, 0);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle params = getArguments();
        questionText = params.getString("Question");
        correctAnswer = params.getString("Correct");
        incorrectAnswers = params.getString("Incorrect");
        points = params.getString("Points");
        isMp = params.getBoolean("IsMp");

        flag = params.getString("StopWatch");
        stopWatch = inflater.inflate(R.layout.sca_fragment, container, false).findViewById(R.id.stopWatch2);
        return inflater.inflate(R.layout.sca_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AnswerOne = view.findViewById(R.id.scaAnswerOne);
        AnswerTwo = view.findViewById(R.id.scaAnswerTwo);
        AnswerThree = view.findViewById(R.id.scaAnswerThree);
        AnswerFour = view.findViewById(R.id.scaAnswerFour);
        questionTextField = view.findViewById(R.id.scaQuestionTextField);
        pointsField = view.findViewById(R.id.scaPointsField);

        bomb = view.findViewById(R.id.bombButton);
        halfButton = view.findViewById(R.id.halfButton);
        questionTextField.setText(questionText);
        pointsField.setText(points);

        stopWatch = view.findViewById(R.id.stopWatch2);

        fillQuestions(correctAnswer, incorrectAnswers);

        if (flag != null) {
            timer2.start();
        }
    }

    private void fillQuestions(String correctAnswer, String incorrectAnswers) {
        int random = new Random().nextInt(4) + 1;
        String[] wrongAnswers = incorrectAnswers.split(";");

        if(random == 1){
            AnswerOne.setText(correctAnswer);
            AnswerOne.setOnClickListener(correctListener);

            AnswerTwo.setText(wrongAnswers[0]);
            AnswerTwo.setOnClickListener(incorrectListener);

            AnswerOne.setVisibility(View.VISIBLE);
            AnswerTwo.setVisibility(View.VISIBLE);
            AnswerThree.setVisibility(View.VISIBLE);
            AnswerFour.setVisibility(View.VISIBLE);

            Bomb(1);
            HalfHalf(1);

            if(wrongAnswers.length != 1){
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

            AnswerOne.setVisibility(View.VISIBLE);
            AnswerTwo.setVisibility(View.VISIBLE);
            AnswerThree.setVisibility(View.VISIBLE);
            AnswerFour.setVisibility(View.VISIBLE);

            Bomb(2);
            HalfHalf(2);
            if(wrongAnswers.length != 1){
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

            AnswerOne.setVisibility(View.VISIBLE);
            AnswerTwo.setVisibility(View.VISIBLE);
            AnswerThree.setVisibility(View.VISIBLE);
            AnswerFour.setVisibility(View.VISIBLE);

            Bomb(3);
            HalfHalf(3);
            if(wrongAnswers.length != 1){
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

            AnswerOne.setVisibility(View.VISIBLE);
            AnswerTwo.setVisibility(View.VISIBLE);
            AnswerThree.setVisibility(View.VISIBLE);
            AnswerFour.setVisibility(View.VISIBLE);

            Bomb(4);
            HalfHalf(4);
            if(wrongAnswers.length != 1){
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
    public void Bomb(final int a){

        bomb.setOnClickListener(new View.OnClickListener() {

            int b = a;
            @Override
            public void onClick(View v) {
                if(PowerUps.bomb < 1){bomb.setEnabled(false);}
                else if(n < 3) {
                    v.setEnabled(false);
                    halfButton.setEnabled(false);
                    int random = new Random().nextInt(3) + 1;
                    if (b == 1) {
                        if (random == 1) {
                            AnswerTwo.setVisibility(View.INVISIBLE);
                        } else if (random == 2) {
                            AnswerThree.setVisibility(View.INVISIBLE);
                        } else {
                            AnswerFour.setVisibility(View.INVISIBLE);
                        }
                    } else if (b == 2) {
                        if (random == 1) {
                            AnswerOne.setVisibility(View.INVISIBLE);
                        } else if (random == 2) {
                            AnswerThree.setVisibility(View.INVISIBLE);
                        } else {
                            AnswerFour.setVisibility(View.INVISIBLE);
                        }
                    } else if (b == 3) {
                        if (random == 1) {
                            AnswerTwo.setVisibility(View.INVISIBLE);
                        } else if (random == 2) {
                            AnswerOne.setVisibility(View.INVISIBLE);
                        } else {
                            AnswerFour.setVisibility(View.INVISIBLE);
                        }
                    } else if (b == 4) {
                        if (random == 1) {
                            AnswerTwo.setVisibility(View.INVISIBLE);
                        } else if (random == 2) {
                            AnswerThree.setVisibility(View.INVISIBLE);
                        } else {
                            AnswerOne.setVisibility(View.INVISIBLE);
                        }
                    }

                    n++;
                    Counter.setCounter(n);
                    int x = PowerUps.bomb;
                    x--;
                    PowerUps.setBomb(x);
                }else{
                    v.setEnabled(false);
                    halfButton.setEnabled(false);
                }
            }
        });
    }

    public void HalfHalf(final int a) {

        halfButton.setOnClickListener(new View.OnClickListener() {
            int b = a;

            @Override
            public void onClick(View v) {
                if (PowerUps.half < 1) {
                    halfButton.setEnabled(false);
                } else if (n < 3) {
                    int random = new Random().nextInt(3) + 1;
                    if (b == 1) {
                        if (random == 1) {
                            AnswerTwo.setVisibility(View.INVISIBLE);
                            AnswerThree.setVisibility(View.INVISIBLE);
                        } else if (random == 2) {
                            AnswerTwo.setVisibility(View.INVISIBLE);
                            AnswerFour.setVisibility(View.INVISIBLE);
                        } else {
                            AnswerThree.setVisibility(View.INVISIBLE);
                            AnswerFour.setVisibility(View.INVISIBLE);
                        }
                    } else if (b == 2) {
                        if (random == 1) {
                            AnswerOne.setVisibility(View.INVISIBLE);
                            AnswerThree.setVisibility(View.INVISIBLE);
                        } else if (random == 2) {
                            AnswerOne.setVisibility(View.INVISIBLE);
                            AnswerFour.setVisibility(View.INVISIBLE);
                        } else {
                            AnswerThree.setVisibility(View.INVISIBLE);
                            AnswerFour.setVisibility(View.INVISIBLE);
                        }
                    } else if (b == 3) {
                        if (random == 1) {
                            AnswerTwo.setVisibility(View.INVISIBLE);
                            AnswerOne.setVisibility(View.INVISIBLE);
                        } else if (random == 2) {
                            AnswerTwo.setVisibility(View.INVISIBLE);
                            AnswerFour.setVisibility(View.INVISIBLE);
                        } else {
                            AnswerOne.setVisibility(View.INVISIBLE);
                            AnswerFour.setVisibility(View.INVISIBLE);
                        }
                    } else if (b == 4) {
                        if (random == 1) {
                            AnswerTwo.setVisibility(View.INVISIBLE);
                            AnswerThree.setVisibility(View.INVISIBLE);
                        } else if (random == 2) {
                            AnswerTwo.setVisibility(View.INVISIBLE);
                            AnswerOne.setVisibility(View.INVISIBLE);
                        } else {
                            AnswerThree.setVisibility(View.INVISIBLE);
                            AnswerOne.setVisibility(View.INVISIBLE);
                        }
                    }
                    v.setEnabled(false);
                    bomb.setEnabled(false);
                    n++;
                    Counter.setCounter(n);
                    int x = PowerUps.half;
                    x--;
                    PowerUps.setHalf(x);
                } else {
                    v.setEnabled(false);
                    bomb.setEnabled(false);
                }
            }
        });
    }
}
