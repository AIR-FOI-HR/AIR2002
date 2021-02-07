package com.example.trivialy;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.responses.GetDataService;
import com.responses.RetrofitInstance;
import com.responses.User.UpdateUserRequest;
import com.responses.User.UpdateUserResponse;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static java.lang.Thread.sleep;


public class HealthRegen extends IntentService {
    Context context;
    String savedUsername;
    Integer savedLives;


    public HealthRegen() {
        super("HealthRegenTimer");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        GetUserData();
        try {
            sleep(10 * 1000);
            UpdateLifeCount(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(savedLives < 5){
            context = getApplicationContext();
            Intent newIntent = new Intent(context, com.example.trivialy.HealthRegen.class);
            startService(newIntent);
        }
        return;
    }

    private void UpdateLifeCount(int value){
        context = getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserData", context.MODE_PRIVATE);
        savedUsername = sharedPreferences.getString("Username", null);
        savedLives = sharedPreferences.getInt("Lives", 0);

        savedLives += value;
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
                    Toast t = Toast.makeText(context , String.valueOf(response.code()), Toast.LENGTH_SHORT);
                    t.show();
                    return;
                }else{
                    if (response.body().getStatus().equals(Integer.toString(1))){
                        //Toast t = Toast.makeText(context , response.body().getText(), Toast.LENGTH_SHORT);
                        //t.show();
                    }else if(response.body().getStatus().equals(Integer.toString(-1))){
                        Toast t = Toast.makeText(context , response.body().getText(), Toast.LENGTH_SHORT);
                        t.show();
                    }else if(response.body().getStatus().equals(Integer.toString(-9)))
                    {
                        Toast t = Toast.makeText(context , response.body().getText(), Toast.LENGTH_SHORT);
                        t.show();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast t1 = Toast.makeText(context , t.getMessage(), Toast.LENGTH_SHORT);
                t1.show();
            }
        });
    }

    private void GetUserData() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        savedUsername = sharedPreferences.getString("Username", null);
        savedLives = sharedPreferences.getInt("Lives", 0);
    }
}
