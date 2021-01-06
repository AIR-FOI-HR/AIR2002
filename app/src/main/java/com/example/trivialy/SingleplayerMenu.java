package com.example.trivialy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class SingleplayerMenu extends AppCompatActivity {
    private long savedDate;
    private Integer savedLives;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleplayer_menu);
        final TextView Lives2 = (TextView) findViewById(R.id.numberOfLives);


        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        final TextView Lives = (TextView) findViewById(R.id.numberOfLives);
                        loadRunoutData();
                        updateLivesCount();

                    }
                });
            }
        }, 0, 5000);


        //za testiranje
        Button testButton = findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Integer LivesInteger = Integer.valueOf((String) Lives2.getText());
                LivesInteger -= 1;
                Lives2.setText(LivesInteger.toString());
                if(LivesInteger < 5){
                    saveRunoutData(LivesInteger);
                }
            }
        });
        Button testButton2 = findViewById(R.id.testButton2);
        testButton2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Integer LivesInteger2 = Integer.valueOf((String) Lives2.getText());
                LivesInteger2 += 1;
                Lives2.setText(LivesInteger2.toString());
                if(LivesInteger2 < 5){
                    saveRunoutData(LivesInteger2);
                }
            }
        });

        Button ExpertModeButton = findViewById(R.id.expertModeButton);
        ExpertModeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent newIntent = new Intent(view.getContext(), ExpertModePlay.class);
                view.getContext().startActivity(newIntent);

                Integer LivesInteger = Integer.valueOf((String) Lives2.getText());
                LivesInteger -= 1;
                Lives2.setText(LivesInteger.toString());
                if(LivesInteger < 5){
                    saveRunoutData(LivesInteger);
                }
            }
        });
    }

    private void updateLivesCount() {
        final TextView Lives = (TextView) findViewById(R.id.numberOfLives);
        if (savedDate != 0){
            Date date = new Date(System.currentTimeMillis());
            long currentDate = date.getTime();

            long diff = getDifference(currentDate, savedDate);
            double minutes = (double) ((double)diff/1000)/60;
            double add;
            int addition;
            if(savedLives < 0) {
                savedLives = 0;
            }
            minutes += savedLives*5;

            if(minutes < 5){
                Lives.setText("0");
            }
            if(minutes >= 5){
                Lives.setText("1");
                add = (minutes-5)*60000;
                addition = (int)add;
                saveRunoutData(1,addition);
            }
            if(minutes >= 10){
                Lives.setText("2");
                add = (minutes-10)*60000;
                addition = (int)add;
                saveRunoutData(2,addition);
            }
            if(minutes >= 15){
                Lives.setText("3");
                add = (minutes-15)*60000;
                addition = (int)add;
                saveRunoutData(3,addition);
            }
            if(minutes >= 20){
                Lives.setText("4");
                add = (minutes-20)*60000;
                addition = (int)add;
                saveRunoutData(4,addition);
            }
            if(minutes >= 25){
                Lives.setText("5");
                add = (minutes-25)*60000;
                addition = (int)add;
                saveRunoutData(5,addition);
            }
        }
        else{
            Lives.setText("5");
        }
    }

    public static long getDifference(long currentDateInMilliseconds, long savedDateInMilliseconds)
    {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        Date dateEnd = new java.util.Date(currentDateInMilliseconds);
        Date dateStart = new java.util.Date(savedDateInMilliseconds);

        return  dateEnd.getTime()-dateStart.getTime();
    }

    private void saveRunoutData(Integer lives) {
        SharedPreferences sharedPreferences = getSharedPreferences("TimeOfRunout", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Date date = new Date(System.currentTimeMillis());
        long datetime = date.getTime();
        editor.putLong("RunoutTime", datetime);
        editor.putInt("LivesCount", lives);
        editor.commit();
    }

    private void saveRunoutData(Integer lives, long addition) {
        SharedPreferences sharedPreferences = getSharedPreferences("TimeOfRunout", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Date date = new Date(System.currentTimeMillis());
        long datetime = date.getTime();
        editor.putLong("RunoutTime", datetime - addition);
        editor.putInt("LivesCount", lives);
        editor.commit();
    }

    private void loadRunoutData() {
        SharedPreferences sharedPreferences = getSharedPreferences("TimeOfRunout", MODE_PRIVATE);
        savedDate = sharedPreferences.getLong("RunoutTime", 0);
        savedLives = sharedPreferences.getInt("LivesCount", 0);
    }

}
