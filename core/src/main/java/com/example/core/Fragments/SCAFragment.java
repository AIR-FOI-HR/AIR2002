package com.example.core.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    public SCAFragment() {}

    callbackInterface callback;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            callback = (callbackInterface) context;
        }
        catch (ClassCastException e){
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

        fillQuestions(correctAnswer, incorrectAnswers);
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
}
