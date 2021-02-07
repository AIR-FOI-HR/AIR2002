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
    Handler handler = new Handler();
    Runnable runnable;
    int delay = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleplayer_menu);
        Lives = (TextView) findViewById(R.id.numberOfLives);
        Lives.setText("0");
        GetUserData();
        Lives.setText(savedLives.toString());

        Intent intent = new Intent(SingleplayerMenu.this, com.example.trivialy.HealthRegen.class);
        boolean check = isMyServiceRunning(com.example.trivialy.HealthRegen.class);
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
                    Intent newIntent = new Intent(view.getContext(), ExpertModePlay.class);
                    view.getContext().startActivity(newIntent);

                    UpdateLifeCount(Lives, -1);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainMenu.class);
        SingleplayerMenu.this.startActivity(intent);
    }

    @Override
    protected void onResume() {
        handler.postDelayed( runnable = new Runnable() {
            public void run() {
                GetUserData();
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

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(getApplicationContext().ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private void GetUserData() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        savedUsername = sharedPreferences.getString("Username", null);
        savedLives = sharedPreferences.getInt("Lives", 0);
    }

    private void UpdateLifeCount(TextView lives, int value){
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        savedUsername = sharedPreferences.getString("Username", null);
        savedLives = sharedPreferences.getInt("Lives", 0);

        savedLives += value;
        lives.setText(savedLives.toString());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Lives", savedLives);
        editor.commit();

        UpdateDBData(savedUsername, savedLives, null);
    }

    private void UpdateDBData(final String savedUsername, Integer savedLives, Integer savedScore) {
        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        UpdateUserRequest request = new UpdateUserRequest(savedUsername, savedLives, savedScore);
        Call<UpdateUserResponse> call = getDataService.updateUserStatus(request);
        call.enqueue(new Callback<UpdateUserResponse>() {
            @Override
            public void onResponse(Response<UpdateUserResponse> response, Retrofit retrofit) {
                if (!response.isSuccess()){
                    Toast t = Toast.makeText(getApplicationContext() , String.valueOf(response.code()), Toast.LENGTH_SHORT);
                    t.show();
                    return;
                }else{
                    if (response.body().getStatus().equals(Integer.toString(1))){
                        //Toast t = Toast.makeText(getApplicationContext() , response.body().getText(), Toast.LENGTH_SHORT);
                        //t.show();
                    }else if(response.body().getStatus().equals(Integer.toString(-1))){
                        Toast t = Toast.makeText(getApplicationContext() , response.body().getText() + savedUsername, Toast.LENGTH_SHORT);
                        t.show();
                    }else if(response.body().getStatus().equals(Integer.toString(-9)))
                    {
                        Toast t = Toast.makeText(getApplicationContext() , response.body().getText(), Toast.LENGTH_SHORT);
                        t.show();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast t1 = Toast.makeText(getApplicationContext() , t.getMessage(), Toast.LENGTH_SHORT);
                t1.show();
            }
        });
    }

}
