package com.example.trivialy;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SingleplayerScore extends AppCompatActivity {
    TextView FinalScore;
    Button HomeButton;
    Button PlayAgain;

    Integer savedLives;
    String savedUsername;

    UserDataController userDataController;
    UserDataController.UserLives userLives;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleplayer_score);
        userDataController = new UserDataController(getApplicationContext());
        Bundle b = getIntent().getExtras();
        String score = "0";
        if(b != null) {
            score = b.getString("Score");
        }

        FinalScore = findViewById(R.id.score_singleplayer);
        FinalScore.setText(score);

        HomeButton = findViewById(R.id.homeButtonExpert);
        PlayAgain = findViewById(R.id.playAgainExpert);

        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SingleplayerMenu.class);
                view.getContext().startActivity(intent);
                finish();
            }
        });

        PlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDataController.GetUserData();
                userLives = userDataController.GetUserData();
                savedUsername = userLives.Username;
                savedLives = userLives.Lives;

                Intent intent = new Intent(SingleplayerScore.this, com.example.trivialy.HealthRegen.class);
                boolean check = userDataController.isMyServiceRunning(com.example.trivialy.HealthRegen.class);
                if(!check && savedLives<5){
                    startService(intent);
                }

                if(savedLives <= 0){
                    Toast t = Toast.makeText(getApplicationContext(), getString(R.string.insufficientLives), Toast.LENGTH_SHORT);
                    t.show();
                    return;
                }
                else {
                    userDataController.UpdateLifeCount(null, -1);
                    Intent newIntent = new Intent(view.getContext(), ExpertMode.class);
                    view.getContext().startActivity(newIntent);
                    finish();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), SingleplayerMenu.class);
        SingleplayerScore.this.startActivity(intent);
        finish();
    }
}
