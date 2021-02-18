package com.example.trivialy;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.responses.GetDataService;
import com.responses.RetrofitInstance;
import com.responses.User.UpdateUserRequest;
import com.responses.User.UpdateUserResponse;


import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class UserDataController {

    Integer savedLives;
    String savedUsername;
    Context context;

    class UserLives{
        Integer Lives;
        String Username;
    }

    public UserDataController(Context context){
        this.context = context;
    }

    public boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public UserLives GetUserData() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserData", context.MODE_PRIVATE);
        savedUsername = sharedPreferences.getString("Username", null);
        savedLives = sharedPreferences.getInt("Lives", 0);

        UserLives userLives = new UserLives();
        userLives.Lives = savedLives;
        userLives.Username = savedUsername;

        return userLives;
    }

    public void UpdateLifeCount(TextView lives, int value){
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserData", context.MODE_PRIVATE);
        savedUsername = sharedPreferences.getString("Username", null);
        savedLives = sharedPreferences.getInt("Lives", 0);

        savedLives += value;
        if(lives != null){
            lives.setText(savedLives.toString());
        }
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
                        //Toast t = Toast.makeText(getApplicationContext() , response.body().getText(), Toast.LENGTH_SHORT);
                        //t.show();
                    }else if(response.body().getStatus().equals(Integer.toString(-1))){
                        Toast t = Toast.makeText(context , response.body().getText() + savedUsername, Toast.LENGTH_SHORT);
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
}
