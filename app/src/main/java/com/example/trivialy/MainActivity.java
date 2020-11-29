package com.example.trivialy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trivialy.Fragments.LoginFragment;
import com.example.trivialy.Fragments.RegistrationFragment;
import com.responses.GetDataService;
import com.responses.RetrofitInstance;

import com.responses.User.LoginRequest;
import com.responses.User.LoginResponse;
import com.responses.User.RegisterRequest;
import com.responses.User.RegisterResponse;

import java.io.Serializable;
import java.util.ArrayList;

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

    public void userLogin(View v){

        logUsername = findViewById(R.id.et_username);
        logPassword = findViewById(R.id.et_passwordd);

        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        LoginRequest request = new LoginRequest(logUsername.getText().toString(), logPassword.getText().toString());
        Call<LoginResponse> call = getDataService.login(request);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Response<LoginResponse> response, Retrofit retrofit) {
                if (!response.isSuccess()){
                   Toast t = Toast.makeText(getApplicationContext() , response.code(), Toast.LENGTH_SHORT);
                    t.show();
                    return;
                }else{
                    if (response.body().getStatus() == Integer.toString(-1)){
                        Toast t = Toast.makeText(getApplicationContext() , response.body().getText(), Toast.LENGTH_SHORT);
                        t.show();

                    }else if(response.body().getStatus() == Integer.toString(-2)){
                        Toast t = Toast.makeText(getApplicationContext() , response.body().getText(), Toast.LENGTH_SHORT);
                        t.show();

                    }else if(response.body().getStatus() == Integer.toString(-9))
                    {
                        Toast t = Toast.makeText(getApplicationContext() , response.body().getText(), Toast.LENGTH_SHORT);
                        t.show();
                    }
                    else if(response.body().getStatus() == Integer.toString(1)){
                        Toast t = Toast.makeText(getApplicationContext() , "Successful login!", Toast.LENGTH_SHORT);
                        t.show();
                        Intent intent = new Intent(getApplicationContext(), CategoryView.class);
                        //intent.putExtra("Login", (Serializable) response.body());
                        startActivity(intent);
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

    boolean isEmail(String text) {
        CharSequence email = text;
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    boolean isEmpty(String text) {
        CharSequence str = text;
        return TextUtils.isEmpty(str);
    }

    public void userRegistration(View v){
        regFirstName = findViewById(R.id.et_firstName);
        regLastName = findViewById(R.id.et_lastName);
        regUsername = findViewById(R.id.et_regUsername);
        regEmail= findViewById(R.id.et_regEmail);
        regPassword= findViewById(R.id.et_password);
        regRepassword = findViewById(R.id.et_repassword);

        String firstName = regFirstName.getText().toString();
        String lastName = regLastName.getText().toString();
        String username = regUsername.getText().toString();
        String email = regEmail.getText().toString();
        String password = regPassword.getText().toString();
        String repassword = regRepassword.getText().toString();
        int greske=0;

        if(isEmpty(firstName)){
            Toast t = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_SHORT);
            t.show();
            greske++;
        }
        if(isEmpty(lastName)){
            Toast t = Toast.makeText(this, "You must enter last name to register!", Toast.LENGTH_SHORT);
            t.show();
            greske++;
        }
        if(isEmpty(username)){
            Toast t = Toast.makeText(this, "You must enter username to register!", Toast.LENGTH_SHORT);
            t.show();
            greske++;
        }
        if(isEmpty(email)){
            Toast t = Toast.makeText(this, "You must enter email to register!", Toast.LENGTH_SHORT);
            t.show();
            greske++;
        }
        if (!isEmail(email)) {
            Toast t= Toast.makeText(this, "Wrong format for email!", Toast.LENGTH_SHORT);
            t.show();
            greske++;
        }
        if(isEmpty(password)){
            Toast t= Toast.makeText(this, "You must enter password to register!", Toast.LENGTH_SHORT);
            t.show();
            greske++;
        }
        if(!password.equals(repassword)){
            Toast t= Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_SHORT);
            t.show();
            greske++;
        }
        if (greske==0){
            GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
            RegisterRequest request = new RegisterRequest(username, password, firstName, lastName,email);
            Call<RegisterResponse> call = getDataService.registerUser(request);

            call.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Response<RegisterResponse> response, Retrofit retrofit) {
                    if (!response.isSuccess()){
                        Toast t = Toast.makeText(getApplicationContext() , response.code(), Toast.LENGTH_SHORT);
                        t.show();
                        return;
                    }else{
                        if (response.body().getStatus() == Integer.toString(-1)){
                            Toast t = Toast.makeText(getApplicationContext() , response.body().getText(), Toast.LENGTH_SHORT);
                            t.show();

                        }else if(response.body().getStatus() == Integer.toString(-2)){
                            Toast t = Toast.makeText(getApplicationContext() , response.body().getText(), Toast.LENGTH_SHORT);
                            t.show();

                        }else if(response.body().getStatus() == Integer.toString(-9))
                        {
                            Toast t = Toast.makeText(getApplicationContext() , response.body().getText(), Toast.LENGTH_SHORT);
                            t.show();
                        }
                        else if (response.body().getStatus() == Integer.toString(1)){
                            Toast t = Toast.makeText(getApplicationContext() , "Your account has been successfully created!", Toast.LENGTH_SHORT);
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

}
class AuthenticationPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    public AuthenticationPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }
    @Override
    public int getCount() {
        return fragmentList.size();
    }
    void addFragmet(Fragment fragment) {
        fragmentList.add(fragment);
    }

}


