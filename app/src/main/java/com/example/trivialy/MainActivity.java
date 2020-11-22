package com.example.trivialy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trivialy.Fragments.LoginFragment;
import com.example.trivialy.Fragments.RegistrationFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    EditText name;
    EditText email;
    EditText password;
    EditText repassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewPager);
        AuthenticationPagerAdapter pagerAdapter = new AuthenticationPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragmet(new LoginFragment());
        pagerAdapter.addFragmet(new RegistrationFragment());
        viewPager.setAdapter(pagerAdapter);
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void checkDataEntered(View v){
        name = findViewById(R.id.et_name);
        email= findViewById(R.id.et_email);
        password= findViewById(R.id.et_password);
        repassword = findViewById(R.id.et_repassword);

        if(isEmpty(name)){
            Toast t = Toast.makeText(this, "You must enter name to register!", Toast.LENGTH_SHORT);
            t.show();
        }

        if (!isEmail(email)) {
            Toast t= Toast.makeText(this, "Wrong format for email!", Toast.LENGTH_SHORT);
            t.show();
        }

        if(isEmpty(password)){
            Toast t= Toast.makeText(this, "You must enter password to register!", Toast.LENGTH_SHORT);
            t.show();
        }

        if(!password.equals(repassword)){
            Toast t= Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_SHORT);
            t.show();
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


