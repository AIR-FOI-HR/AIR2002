package com.example.trivialy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.core.PowerUps;
import com.example.trivialy.Fragments.AuthenticationPagerAdapter;
import com.example.trivialy.Fragments.LoginFragment;
import com.example.trivialy.Fragments.RegistrationFragment;
import com.responses.GetDataService;
import com.responses.Leaderboard.QuizScoreboardRequest;
import com.responses.Leaderboard.QuizScoreboardResponse;
import com.responses.Leaderboard.Scoreboard;
import com.responses.PowerUps.PowerUp;
import com.responses.PowerUps.PowerUpRequest;
import com.responses.PowerUps.PowerUpResponse;
import com.responses.RetrofitInstance;

import com.responses.User.LoginRequest;
import com.responses.User.LoginResponse;
import com.responses.User.RegisterRequest;
import com.responses.User.RegisterResponse;

import java.io.Serializable;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements Serializable {

    EditText regFirstName;
    EditText regLastName;
    EditText regUsername;
    EditText regEmail;
    EditText regPassword;
    EditText regRepassword;
    EditText logPassword;
    EditText logUsername;

    List<PowerUp> lista;
    List<Scoreboard> lista2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeUi();
    }

    private void initializeUi() {
        ViewPager viewPager = findViewById(R.id.viewPager);
        AuthenticationPagerAdapter pagerAdapter = new AuthenticationPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragmet(new LoginFragment());
        pagerAdapter.addFragmet(new RegistrationFragment());
        viewPager.setAdapter(pagerAdapter);
    }

    public void Scoreboard(int quizid) {
        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        QuizScoreboardRequest request = new QuizScoreboardRequest(quizid);
        Call<QuizScoreboardResponse> call = getDataService.getScoreboard(request);
        call.enqueue(new Callback<QuizScoreboardResponse>() {
            @Override
            public void onResponse(Response<QuizScoreboardResponse> response, Retrofit retrofit) {
                if (!response.isSuccess()) {


                    return;
                } else {
                    if (response.body().getStatus() == 1) {
                        lista2 = response.body().getScoreboard();

                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }


    public void UserPowerUps(String username){
        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        PowerUpRequest request = new PowerUpRequest(username);
        Call<PowerUpResponse> call = getDataService.GetPowerUps(request);
        call.enqueue(new Callback<PowerUpResponse>() {
            @Override
            public void onResponse(Response<PowerUpResponse> response, Retrofit retrofit) {
                if (!response.isSuccess()) {


                    return;
                } else {
                    if (response.body().getStatus()== 1){
                        lista = response.body().getUserPowerups();
                        for (PowerUp p : lista
                        ) {
                            if(p.getName().equals("50/50")){
                                PowerUps.setHalf(p.getAmount());
                                PowerUps.setHalfId(p.getPowerupId());
                            }else if(p.getName().equals("Bomba")){
                                PowerUps.setBomb(p.getAmount());
                                PowerUps.setBombId(p.getPowerupId());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void userLogin(View v) {

        logUsername = findViewById(R.id.et_username);
        logPassword = findViewById(R.id.et_passwordd);

        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        LoginRequest request = new LoginRequest(logUsername.getText().toString(), logPassword.getText().toString());
        Call<LoginResponse> call = getDataService.login(request);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Response<LoginResponse> response, Retrofit retrofit) {
                if (!response.isSuccess()) {
                    Toast t = Toast.makeText(getApplicationContext(), String.valueOf(response.code()), Toast.LENGTH_SHORT);
                    t.show();
                    return;
                } else {
                    if (response.body().getStatus() == -1) {
                        Toast t = Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_SHORT);
                        t.show();

                    } else if (response.body().getStatus()==-2) {
                        Toast t = Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_SHORT);
                        t.show();

                    } else if (response.body().getStatus()==-9) {
                        Toast t = Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_SHORT);
                        t.show();
                    } else if (response.body().getStatus()==1) {
                        Toast t = Toast.makeText(getApplicationContext(), "Successful login!", Toast.LENGTH_SHORT);
                        t.show();
                        UserPowerUps(response.body().getUsername());
                        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("FirstName", response.body().getFirstname());
                        editor.putString("LastName", response.body().getLastname());
                        editor.putString("Username", response.body().getUsername());
                        editor.putInt("Score", Integer.valueOf(response.body().getScore()));
                        editor.putInt("Lives", Integer.valueOf(response.body().getLife()));
                        editor.commit();

                        Intent intent = new Intent(getApplicationContext(), MainMenu.class);

                        MainActivity.this.startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast t1 = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT);
                t1.show();
            }
        });

    }

    boolean isEmail(String text) {
        CharSequence email = text;
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(String text) {
        CharSequence str = text;
        return TextUtils.isEmpty(str);
    }

    public void userRegistration(View v) {
        regFirstName = findViewById(R.id.et_firstName);
        regLastName = findViewById(R.id.et_lastName);
        regUsername = findViewById(R.id.et_regUsername);
        regEmail = findViewById(R.id.et_regEmail);
        regPassword = findViewById(R.id.et_password);
        regRepassword = findViewById(R.id.et_repassword);

        String firstName = regFirstName.getText().toString();
        String lastName = regLastName.getText().toString();
        final String username = regUsername.getText().toString();
        String email = regEmail.getText().toString();
        String password = regPassword.getText().toString();
        String repassword = regRepassword.getText().toString();
        int greske = 0;

        if (isEmpty(firstName)) {
            Toast t = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_SHORT);
            t.show();
            greske++;
        }
        if (isEmpty(lastName)) {
            Toast t = Toast.makeText(this, "You must enter last name to register!", Toast.LENGTH_SHORT);
            t.show();
            greske++;
        }
        if (isEmpty(username)) {
            Toast t = Toast.makeText(this, "You must enter username to register!", Toast.LENGTH_SHORT);
            t.show();
            greske++;
        }
        if (isEmpty(email)) {
            Toast t = Toast.makeText(this, "You must enter email to register!", Toast.LENGTH_SHORT);
            t.show();
            greske++;
        }
        if (!isEmail(email)) {
            Toast t = Toast.makeText(this, "Wrong format for email!", Toast.LENGTH_SHORT);
            t.show();
            greske++;
        }
        if (isEmpty(password)) {
            Toast t = Toast.makeText(this, "You must enter password to register!", Toast.LENGTH_SHORT);
            t.show();
            greske++;
        }
        if (!password.equals(repassword)) {
            Toast t = Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_SHORT);
            t.show();
            greske++;
        }
        if (greske == 0) {
            GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
            RegisterRequest request = new RegisterRequest(username, password, firstName, lastName, email);
            Call<RegisterResponse> call = getDataService.registerUser(request);

            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Response<RegisterResponse> response, Retrofit retrofit) {
                    if (!response.isSuccess()) {
                        Toast t = Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_SHORT);
                        t.show();
                        return;
                    } else {
                        if (response.body().getStatus() ==-1) {
                            Toast t = Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_SHORT);
                            t.show();

                        } else if (response.body().getStatus() == -2) {
                            Toast t = Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_SHORT);
                            t.show();

                        } else if (response.body().getStatus() == -9) {
                            Toast t = Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_SHORT);
                            t.show();
                        } else if (response.body().getStatus() == 1) {
                            Toast t = Toast.makeText(getApplicationContext(), "Your account has been successfully created!", Toast.LENGTH_SHORT);
                            t.show();

                            regFirstName.setText("");
                            regLastName.setText("");
                            regUsername.setText("");
                            regEmail.setText("");
                            regPassword.setText("");
                            regRepassword.setText("");
                        }
                        SingleplayerScore.SetPowerUps(username,1,1);
                        SingleplayerScore.SetPowerUps(username,2,2);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast t1 = Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT);
                    t1.show();
                }
            });

        }
    }

}


