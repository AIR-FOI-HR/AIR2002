package com.example.trivialy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.responses.GetDataService;
import com.responses.Quiz.AvailableQuizListResponse;
import com.responses.RetrofitInstance;
import com.responses.Quiz.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class AvailableQuizesMenu extends AppCompatActivity {
    int odabranaKategorija = getIntent().getIntExtra("odabranaKategorija", 0);
    private ListView lv;
    ArrayList<Quiz> quizes = new ArrayList<>();
    ArrayList<LocalDateTime> vremenaPocetakaKviza = new ArrayList<>();
    ArrayList<String> imenaKviza = new ArrayList<>();

    UserDataController userDataController;
    UserDataController.UserLives userLives;
    String currentUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.available_quizes_menu);
        lv =(ListView) findViewById(R.id.listviewAQ);
        userDataController = new UserDataController(getApplicationContext());
        currentUser = userLives.Username;

        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        final Call<List<Quiz>> call = getDataService.GetAvailableQuizes(odabranaKategorija);

        call.enqueue(new Callback<List<Quiz>>() {
            @Override
            public void onResponse(Response<List<Quiz>> response, Retrofit retrofit) {
                quizes = (ArrayList<Quiz>) response.body();
                for (Quiz c : quizes) {
                    vremenaPocetakaKviza.add(c.getStartDate());
                    imenaKviza.add(c.getName());
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, imenaKviza) {

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        // Get the Item from ListView
                        View view = super.getView(position, convertView, parent);
                        // Initialize a TextView for ListView each Item
                        TextView tv = (TextView) view.findViewById(android.R.id.text1);

                        // Set the text color of TextView (ListView Item)
                        tv.setTextColor(Color.BLACK);

                        // Generate ListView Item using TextView
                        return view;
                    }
                };

                lv.setAdapter(arrayAdapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent newIntent = new Intent(getApplicationContext(), Lobby.class);
                        newIntent.putExtra("odabraniKviz", (int) lv.getItemAtPosition(position));
                        newIntent.putExtra("trenutniKorisnik", (String) currentUser);
                        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
                        AvailableQuizesMenu.this.startActivity(newIntent);
                        finish();
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {
                Toast t1 = Toast.makeText(getApplicationContext(), "There was an error while loading available quizes!\n" + t.getMessage(), Toast.LENGTH_SHORT);
                t1.show();
            }
        });
    }
}