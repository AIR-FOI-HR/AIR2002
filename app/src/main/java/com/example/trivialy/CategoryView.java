package com.example.trivialy;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.responses.Category;
import com.responses.GetDataService;
import com.responses.RetrofitInstance;
import com.responses.User.LoginRequest;

import java.io.Serializable;
import java.util.List;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class CategoryView extends AppCompatActivity implements Serializable {
    TextView textView;
    LoginRequest login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_listview);
        textView = (TextView)findViewById(R.id.text_view_result);
        //login = (Login) getIntent().getSerializableExtra("Login");
        //textView.setText(login.getFirstname() + " " + login.getLastname());
    }
}
