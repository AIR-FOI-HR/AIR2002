package com.example.trivialy;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.responses.GetDataService;
import com.responses.RetrofitInstance;
import com.responses.User.UpdateUserRequest;
import com.responses.User.UpdateUserResponse;

import java.util.Timer;
import java.util.TimerTask;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class SingleplayerMenu extends AppCompatActivity {
    private String savedUsername;
    private Integer savedLives;
    private TextView Lives = null;
    UserDataController userDataController;
    UserDataController.UserLives userLives;
    Handler handler = new Handler();
    Runnable runnable;
    int delay = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userDataController = new UserDataController(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleplayer_menu);
        Lives = (TextView) findViewById(R.id.numberOfLives);
        Lives.setText("0");
        userLives = userDataController.GetUserData();
        savedUsername = userLives.Username;
        savedLives = userLives.Lives;

        Lives.setText(savedLives.toString());

        Intent intent = new Intent(SingleplayerMenu.this, com.example.trivialy.HealthRegen.class);
        boolean check = userDataController.isMyServiceRunning(com.example.trivialy.HealthRegen.class);
        if(!check && Integer.valueOf((String) Lives.getText())<5){
            startService(intent);
        }

        Button ExpertModeButton = findViewById(R.id.expertModeButton);
        ExpertModeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(Integer.valueOf((String) Lives.getText()) <= 0){
                    Toast t = Toast.makeText(getApplicationContext(), getString(R.string.insufficientLives), Toast.LENGTH_SHORT);
                    t.show();
                    return;
                }
                else {
                    userDataController.UpdateLifeCount(Lives, -1);

                    Intent intent = new Intent(SingleplayerMenu.this, com.example.trivialy.HealthRegen.class);
                    boolean check = userDataController.isMyServiceRunning(com.example.trivialy.HealthRegen.class);
                    if(!check && Integer.valueOf((String) Lives.getText())<5){
                        startService(intent);
                    }

                    Intent newIntent = new Intent(view.getContext(), ExpertModePlay.class);
                    view.getContext().startActivity(newIntent);
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainMenu.class);
        SingleplayerMenu.this.startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        handler.postDelayed( runnable = new Runnable() {
            public void run() {
                userLives = userDataController.GetUserData();
                savedUsername = userLives.Username;
                savedLives = userLives.Lives;

                Lives.setText(savedLives.toString());
                handler.postDelayed(runnable, delay);
            }
        }, delay);

        super.onResume();
    }


    @Override
    protected void onPause() {
        handler.removeCallbacks(runnable);
        super.onPause();
    }

}
