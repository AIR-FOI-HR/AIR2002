package com.example.trivialy;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.responses.GetDataService;
import com.responses.RetrofitInstance;
import com.responses.User.UpdateUserRequest;
import com.responses.User.UpdateUserResponse;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class SingleplayerMenu extends AppCompatActivity {
    private String savedUsername;
    private Integer savedLives;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleplayer_menu);
        final TextView Lives = (TextView) findViewById(R.id.numberOfLives);
        Lives.setText("0");
        GetUserData();
        Lives.setText(savedLives.toString());


//        new Timer().scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//
//                        final TextView Lives = (TextView) findViewById(R.id.numberOfLives);
//
//                    }
//                });
//            }
//        }, 0, 5000);


        //za testiranje
        Button testButton = findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                UpdateLifeCount(Lives, -1);
            }
        });
        Button testButton2 = findViewById(R.id.testButton2);
        testButton2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                UpdateLifeCount(Lives, 1);
            }
        });

        Button ExpertModeButton = findViewById(R.id.expertModeButton);
        ExpertModeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent newIntent = new Intent(view.getContext(), ExpertModePlay.class);
                view.getContext().startActivity(newIntent);

                UpdateLifeCount(Lives, -1);
                HealthRegen regen = new HealthRegen();

                Intent intent = new Intent(SingleplayerMenu.this, HealthRegen.class);
                if(!isMyServiceRunning(HealthRegen.class)){
                    startService(intent);
                }
            }
        });
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
