package com.example.core.Fragments;

import android.content.Context;
import android.content.Intent;
<<<<<<< HEAD
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
=======
import android.os.Bundle;
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
<<<<<<< HEAD
import androidx.constraintlayout.widget.ConstraintLayout;
=======
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
import androidx.fragment.app.Fragment;

import com.example.core.R;
import com.example.core.callbackInterface;

import java.util.Random;

public class SCAFragment extends Fragment {

    Context context;

    TextView pointsField;
    TextView questionTextField;
    Button AnswerOne;
    Button AnswerTwo;
    Button AnswerThree;
    Button AnswerFour;

    String questionText;
    String correctAnswer;
    String incorrectAnswers;

    String points;

<<<<<<< HEAD
    TextView stopWatch;
    int seconds = 0;
    String prikaz;
    String flag = "Da";

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

    public SCAFragment() {
    }
=======
    public SCAFragment() {}
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf

    callbackInterface callback;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            callback = (callbackInterface) context;
<<<<<<< HEAD
        } catch (ClassCastException e) {
=======
        }
        catch (ClassCastException e){
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
            throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }
    }

    private View.OnClickListener correctListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            callback.onFinnish(true, 1);
        }
    };

    private View.OnClickListener incorrectListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            callback.onFinnish(false, 0);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle params = getArguments();
        questionText = params.getString("Question");
        correctAnswer = params.getString("Correct");
        incorrectAnswers = params.getString("Incorrect");
        points = params.getString("Points");
<<<<<<< HEAD
        flag = params.getString("StopWatch");
        stopWatch = inflater.inflate(R.layout.sca_fragment, container, false).findViewById(R.id.stopWatch2);

=======
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
        return inflater.inflate(R.layout.sca_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        context = view.getContext();

        AnswerOne = view.findViewById(R.id.scaAnswerOne);
        AnswerTwo = view.findViewById(R.id.scaAnswerTwo);
        AnswerThree = view.findViewById(R.id.scaAnswerThree);
        AnswerFour = view.findViewById(R.id.scaAnswerFour);
        questionTextField = view.findViewById(R.id.scaQuestionTextField);
        pointsField = view.findViewById(R.id.scaPointsField);

        questionTextField.setText(questionText);
        pointsField.setText(points);

<<<<<<< HEAD

        stopWatch = view.findViewById(R.id.stopWatch2);


        fillQuestions(correctAnswer, incorrectAnswers);

        if (flag != null) {
            timer2.start();
        }
    }


=======
        fillQuestions(correctAnswer, incorrectAnswers);
    }

>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
    private void fillQuestions(String correctAnswer, String incorrectAnswers) {
        int random = new Random().nextInt(4) + 1;
        String[] wrongAnswers = incorrectAnswers.split(";");

<<<<<<< HEAD
        if (random == 1) {
=======
        if(random == 1){
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
            AnswerOne.setText(correctAnswer);
            AnswerOne.setOnClickListener(correctListener);

            AnswerTwo.setText(wrongAnswers[0]);
            AnswerTwo.setOnClickListener(incorrectListener);

            AnswerOne.setVisibility(View.VISIBLE);
            AnswerTwo.setVisibility(View.VISIBLE);
            AnswerThree.setVisibility(View.VISIBLE);
            AnswerFour.setVisibility(View.VISIBLE);


<<<<<<< HEAD
            if (wrongAnswers.length != 1) {
=======
            if(wrongAnswers.length != 1){
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
                AnswerThree.setText(wrongAnswers[1]);
                AnswerFour.setText(wrongAnswers[2]);

                AnswerThree.setOnClickListener(incorrectListener);
                AnswerFour.setOnClickListener(incorrectListener);
<<<<<<< HEAD
            } else if (wrongAnswers.length == 1) {
=======
            }
            else if(wrongAnswers.length == 1){
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
                AnswerThree.setVisibility(View.GONE);
                AnswerFour.setVisibility(View.GONE);
            }
        }

<<<<<<< HEAD
        if (random == 2) {
=======
        if(random == 2){
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
            AnswerTwo.setText(correctAnswer);
            AnswerTwo.setOnClickListener(correctListener);

            AnswerOne.setText(wrongAnswers[0]);
            AnswerOne.setOnClickListener(incorrectListener);

            AnswerOne.setVisibility(View.VISIBLE);
            AnswerTwo.setVisibility(View.VISIBLE);
            AnswerThree.setVisibility(View.VISIBLE);
            AnswerFour.setVisibility(View.VISIBLE);

<<<<<<< HEAD
            if (wrongAnswers.length != 1) {
=======
            if(wrongAnswers.length != 1){
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
                AnswerThree.setText(wrongAnswers[1]);
                AnswerFour.setText(wrongAnswers[2]);

                AnswerThree.setOnClickListener(incorrectListener);
                AnswerFour.setOnClickListener(incorrectListener);
<<<<<<< HEAD
            } else if (wrongAnswers.length == 1) {
=======
            }
            else if(wrongAnswers.length == 1){
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
                AnswerThree.setVisibility(View.GONE);
                AnswerFour.setVisibility(View.GONE);
            }
        }

<<<<<<< HEAD
        if (random == 3) {
=======
        if(random == 3){
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
            AnswerThree.setText(correctAnswer);
            AnswerThree.setOnClickListener(correctListener);

            AnswerTwo.setText(wrongAnswers[0]);
            AnswerTwo.setOnClickListener(incorrectListener);

            AnswerOne.setVisibility(View.VISIBLE);
            AnswerTwo.setVisibility(View.VISIBLE);
            AnswerThree.setVisibility(View.VISIBLE);
            AnswerFour.setVisibility(View.VISIBLE);

<<<<<<< HEAD
            if (wrongAnswers.length != 1) {
=======
            if(wrongAnswers.length != 1){
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
                AnswerOne.setText(wrongAnswers[1]);
                AnswerFour.setText(wrongAnswers[2]);

                AnswerOne.setOnClickListener(incorrectListener);
                AnswerFour.setOnClickListener(incorrectListener);
<<<<<<< HEAD
            } else if (wrongAnswers.length == 1) {
=======
            }
            else if(wrongAnswers.length == 1){
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
                AnswerOne.setVisibility(View.GONE);
                AnswerFour.setVisibility(View.GONE);
            }
        }

<<<<<<< HEAD
        if (random == 4) {
=======
        if(random == 4){
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
            AnswerFour.setText(correctAnswer);
            AnswerFour.setOnClickListener(correctListener);

            AnswerTwo.setText(wrongAnswers[0]);
            AnswerTwo.setOnClickListener(incorrectListener);

            AnswerOne.setVisibility(View.VISIBLE);
            AnswerTwo.setVisibility(View.VISIBLE);
            AnswerThree.setVisibility(View.VISIBLE);
            AnswerFour.setVisibility(View.VISIBLE);

<<<<<<< HEAD
            if (wrongAnswers.length != 1) {
=======
            if(wrongAnswers.length != 1){
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
                AnswerThree.setText(wrongAnswers[1]);
                AnswerOne.setText(wrongAnswers[2]);

                AnswerThree.setOnClickListener(incorrectListener);
                AnswerOne.setOnClickListener(incorrectListener);
<<<<<<< HEAD
            } else if (wrongAnswers.length == 1) {
=======
            }
            else if(wrongAnswers.length == 1){
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
                AnswerThree.setVisibility(View.GONE);
                AnswerOne.setVisibility(View.GONE);
            }
        }
    }
}
