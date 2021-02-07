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

    UserDataController userDataController;
    UserDataController.UserLives userLives;

    public HealthRegen() {
        super("HealthRegenTimer");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        userDataController = new UserDataController(getApplicationContext());

        userLives = userDataController.GetUserData();
        savedUsername = userLives.Username;
        savedLives = userLives.Lives;

        if(savedLives < 5) {
            try {
                sleep(10 * 1000);
                userDataController.UpdateLifeCount(null, 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        if(savedLives < 5){
            context = getApplicationContext();
            Intent newIntent = new Intent(context, com.example.trivialy.HealthRegen.class);
            startService(newIntent);
        }
        return;
    }
}
