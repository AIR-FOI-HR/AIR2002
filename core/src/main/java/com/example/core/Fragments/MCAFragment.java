package com.example.core.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.core.interfaces.QFragment;
import com.example.core.QuestionData;
import com.example.core.R;
import com.example.core.interfaces.callbackInterface;

import java.util.Random;

public class MCAFragment extends Fragment implements QFragment {

    Context context;

    TextView pointsField;
    TextView questionTextField;
    CheckBox AnswerOne;
    CheckBox AnswerTwo;
    CheckBox AnswerThree;
    CheckBox AnswerFour;

    Button Submit;

    String questionText;
    String correctAnswers;
    String incorrectAnswers;

    String points;
    Integer pointsAdd = 0;
    Integer pointOne = 0;
    Integer pointTwo = 0;
    Integer pointThree = 0;
    Integer pointFour = 0;
    Integer[] correctCheck;

    TextView stopWatch;
    int seconds = 30;
    String prikaz;
    String flag = "Da";

    CountDownTimer timer2 = new CountDownTimer(seconds * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            seconds--;
            prikaz = Integer.toString(seconds);
            stopWatch.setText(prikaz);
        }

        @Override
        public void onFinish() {
            callback.onFinnish(false, 0);
        }
    };

    public MCAFragment() {
    }

    callbackInterface callback;

    private final View.OnClickListener fourListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pointOne = 0;
            pointTwo = 0;
            pointThree = 0;
            pointFour = 0;
            if (AnswerOne.isChecked()) {
                pointOne = 1;
            }
            if (AnswerTwo.isChecked()) {
                pointTwo = 1;
            }
            if (AnswerThree.isChecked()) {
                pointThree = 1;
            }
            if (AnswerFour.isChecked()) {
                pointFour = 1;
            }

            pointsAdd = (pointOne) + (pointTwo) + (pointThree) + (pointFour);

            timer2.cancel();
            callback.onFinnish(pointsAdd > 0, pointsAdd);
        }
    };

    private final View.OnClickListener restListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pointOne = 0;
            pointTwo = 0;
            pointThree = 0;
            pointFour = 0;

            if (AnswerOne.isChecked()) {
                pointOne = 1;
            }
            if (AnswerTwo.isChecked()) {
                pointTwo = 1;
            }
            if (AnswerThree.isChecked()) {
                pointThree = 1;
            }
            if (AnswerFour.isChecked()) {
                pointFour = 1;
            }

            pointsAdd = (pointOne * correctCheck[0]) + (pointTwo * correctCheck[1]) + (pointThree * correctCheck[2]) + (pointFour * correctCheck[3]);
            timer2.cancel();
            callback.onFinnish(pointsAdd > 0, pointsAdd);

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        stopWatch = inflater.inflate(R.layout.mca_fragment, container, false).findViewById(R.id.stopWatch);
        return inflater.inflate(R.layout.mca_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        context = view.getContext();

        AnswerOne = view.findViewById(R.id.mcaAnswerOne);
        AnswerTwo = view.findViewById(R.id.mcaAnswerTwo);
        AnswerThree = view.findViewById(R.id.mcaAnswerThree);
        AnswerFour = view.findViewById(R.id.mcaAnswerFour);
        questionTextField = view.findViewById(R.id.mcaQuestionTextField);
        pointsField = view.findViewById(R.id.mcaPointsField);
        Submit = view.findViewById(R.id.submitButton);

        correctCheck = new Integer[4];

        questionTextField.setText(questionText);
        pointsField.setText(points);


        stopWatch = view.findViewById(R.id.stopWatch);


        fillQuestions(correctAnswers, incorrectAnswers);
        if (flag != null) {
            timer2.start();
        }


        fillQuestions(correctAnswers, incorrectAnswers);
    }

    private void fillQuestions(String correctAnswers, String incorrectAnswers) {
        int random = new Random().nextInt(4) + 1;
        int rand = new Random().nextInt(6) + 1;

        String[] corAnswers = correctAnswers.split(";");
        String[] wrongAnswers = incorrectAnswers.split(";");

        if (corAnswers.length == 4) {
            //svi su tocni
            AnswerOne.setText(corAnswers[0]);
            AnswerTwo.setText(corAnswers[1]);
            AnswerThree.setText(corAnswers[2]);
            AnswerFour.setText(corAnswers[3]);

            Submit.setOnClickListener(fourListener);
        }

        if (corAnswers.length == 3) {
            if (random == 1) {
                AnswerOne.setText(wrongAnswers[0]);
                AnswerTwo.setText(corAnswers[0]);
                AnswerThree.setText(corAnswers[1]);
                AnswerFour.setText(corAnswers[2]);
                correctCheck[0] = -1;
                correctCheck[1] = 1;
                correctCheck[2] = 1;
                correctCheck[3] = 1;
            }
            if (random == 2) {
                AnswerOne.setText(corAnswers[0]);
                AnswerTwo.setText(wrongAnswers[0]);
                AnswerThree.setText(corAnswers[1]);
                AnswerFour.setText(corAnswers[2]);
                correctCheck[0] = 1;
                correctCheck[1] = -1;
                correctCheck[2] = 1;
                correctCheck[3] = 1;
            }
            if (random == 3) {
                AnswerOne.setText(corAnswers[1]);
                AnswerTwo.setText(corAnswers[0]);
                AnswerThree.setText(wrongAnswers[0]);
                AnswerFour.setText(corAnswers[2]);
                correctCheck[0] = 1;
                correctCheck[1] = 1;
                correctCheck[2] = -1;
                correctCheck[3] = 1;
            }
            if (random == 4) {
                AnswerOne.setText(corAnswers[2]);
                AnswerTwo.setText(corAnswers[0]);
                AnswerThree.setText(corAnswers[1]);
                AnswerFour.setText(wrongAnswers[0]);
                correctCheck[0] = 1;
                correctCheck[1] = 1;
                correctCheck[2] = 1;
                correctCheck[3] = -1;
            }

            Submit.setOnClickListener(restListener);
        }

        if (corAnswers.length == 2) {
            if (rand == 1) {
                AnswerOne.setText(wrongAnswers[0]);
                AnswerTwo.setText(wrongAnswers[1]);
                AnswerThree.setText(corAnswers[0]);
                AnswerFour.setText(corAnswers[1]);
                correctCheck[0] = -1;
                correctCheck[1] = -1;
                correctCheck[2] = 1;
                correctCheck[3] = 1;

                Submit.setOnClickListener(restListener);
            }
            if (rand == 2) {
                AnswerOne.setText(wrongAnswers[0]);
                AnswerTwo.setText(corAnswers[0]);
                AnswerThree.setText(wrongAnswers[1]);
                AnswerFour.setText(corAnswers[1]);
                correctCheck[0] = -1;
                correctCheck[1] = 1;
                correctCheck[2] = -1;
                correctCheck[3] = 1;

                Submit.setOnClickListener(restListener);
            }
            if (rand == 3) {
                AnswerOne.setText(wrongAnswers[0]);
                AnswerTwo.setText(corAnswers[1]);
                AnswerThree.setText(corAnswers[0]);
                AnswerFour.setText(wrongAnswers[1]);
                correctCheck[0] = -1;
                correctCheck[1] = 1;
                correctCheck[2] = 1;
                correctCheck[3] = -1;

                Submit.setOnClickListener(restListener);
            }
            if (rand == 4) {
                AnswerOne.setText(corAnswers[0]);
                AnswerTwo.setText(wrongAnswers[1]);
                AnswerThree.setText(wrongAnswers[0]);
                AnswerFour.setText(corAnswers[1]);
                correctCheck[0] = 1;
                correctCheck[1] = -1;
                correctCheck[2] = -1;
                correctCheck[3] = 1;

                Submit.setOnClickListener(restListener);
            }
            if (rand == 5) {
                AnswerOne.setText(corAnswers[0]);
                AnswerTwo.setText(wrongAnswers[0]);
                AnswerThree.setText(corAnswers[1]);
                AnswerFour.setText(wrongAnswers[1]);
                correctCheck[0] = 1;
                correctCheck[1] = -1;
                correctCheck[2] = 1;
                correctCheck[3] = -1;

                Submit.setOnClickListener(restListener);
            }
            if (rand == 6) {
                AnswerOne.setText(corAnswers[0]);
                AnswerTwo.setText(corAnswers[1]);
                AnswerThree.setText(wrongAnswers[0]);
                AnswerFour.setText(wrongAnswers[1]);
                correctCheck[0] = 1;
                correctCheck[1] = 1;
                correctCheck[2] = -1;
                correctCheck[3] = -1;

                Submit.setOnClickListener(restListener);
            }
        }
    }

    @Override
    public Fragment getFragment(QuestionData data, callbackInterface listener) {
        questionText = data.questionText;
        correctAnswers = data.correctAnswers;
        incorrectAnswers = data.incorrectAnswers;
        points = data.points;
        flag = data.Flag;

        callback = listener;
        return this;
    }
}
