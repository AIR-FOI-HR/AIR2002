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

import com.example.core.Counter;
import com.example.core.PowerUps;
import com.responses.GetDataService;
import com.responses.PowerUps.SetUserPowerupStatusRequest;
import com.responses.PowerUps.SetUserPowerupStatusResponse;
import com.responses.RetrofitInstance;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class SingleplayerScore extends AppCompatActivity {
    TextView FinalScore;
    Button HomeButton;
    Button PlayAgain;

    Integer savedLives;
    String savedUsername;

    UserDataController userDataController;
    UserDataController.UserLives userLives;

    public static void SetPowerUps(String username, int powerupId, int amount){
        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        SetUserPowerupStatusRequest request = new SetUserPowerupStatusRequest(username, powerupId, amount);
        Call<SetUserPowerupStatusResponse> call = getDataService.SetPowerUps(request);
        call.enqueue(new Callback<SetUserPowerupStatusResponse>() {
            @Override
            public void onResponse(Response<SetUserPowerupStatusResponse> response, Retrofit retrofit) {
                if (!response.isSuccess()) {
                    return;
                } else {
                    if (response.body().getStatus()== 1){

                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleplayer_score);
        userDataController = new UserDataController(getApplicationContext());

        int counterCorrect = Counter.counterCorrect;

        if(counterCorrect >= 3){
            int bombadd = PowerUps.bomb;
            int halfadd = PowerUps.half;
            bombadd++;
            halfadd++;
            PowerUps.setBomb(bombadd);
            PowerUps.setHalf(halfadd);
        }
        Counter.setCounterCorrect(0);
        SetPowerUps(userDataController.GetUserData().Username, PowerUps.bombId, PowerUps.bomb);
        SetPowerUps(userDataController.GetUserData().Username, PowerUps.halfId, PowerUps.half);


        final String flag = (String) getIntent().getSerializableExtra("flag");
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
                    if (flag.equals("flag")) {
                        userDataController.UpdateLifeCount(null, -1);
                        Intent newIntent = new Intent(view.getContext(), CategoryView.class);
                        view.getContext().startActivity(newIntent);
                        finish();
                    }
                    else if(flag.equals("flag1")){
                        userDataController.UpdateLifeCount(null, -1);
                        Intent newIntent = new Intent(view.getContext(), CategoryViewFreePlay.class);
                        view.getContext().startActivity(newIntent);
                        finish();
                    }
                    else {
                        userDataController.UpdateLifeCount(null, -1);
                        Intent newIntent = new Intent(view.getContext(), ExpertModePlay.class);
                        view.getContext().startActivity(newIntent);
                        finish();
                    }
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
