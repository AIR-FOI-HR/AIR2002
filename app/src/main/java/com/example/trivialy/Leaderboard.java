package com.example.trivialy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.core.PowerUps;
import com.responses.GetDataService;
import com.responses.Leaderboard.QuizScoreboardRequest;
import com.responses.Leaderboard.QuizScoreboardResponse;
import com.responses.Leaderboard.Scoreboard;
import com.responses.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class Leaderboard extends AppCompatActivity {
    private ListView lv;
    List<Scoreboard> scorebaord;
    ArrayList<String> listaImena;
    Integer quizId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leadeboard);
        lv = (ListView) findViewById(R.id.listLd);

        scorebaord = new ArrayList<>();
        listaImena = new ArrayList<>();

        quizId = getIntent().getIntExtra("quizid", 0);

        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        QuizScoreboardRequest request = new QuizScoreboardRequest(quizId);
        Call<QuizScoreboardResponse> call = getDataService.getScoreboard(request);
        call.enqueue(new Callback<QuizScoreboardResponse>() {
            @Override
            public void onResponse(Response<QuizScoreboardResponse> response, Retrofit retrofit) {
                scorebaord = response.body().getScoreboard();
                for (Scoreboard c : scorebaord) {
                    for (int i = 0; i <1; i++){
                        String user = c.getUsername();
                        int bomb = PowerUps.bomb;
                        int half = PowerUps.half;
                        bomb++;
                        half++;
                        PowerUps.setHalf(bomb);
                        PowerUps.setBomb(half);
                        SingleplayerScore.SetPowerUps(user,1,half);
                        SingleplayerScore.SetPowerUps(user,2,bomb);
                    }
                    listaImena.add(c.getUsername() + " | " + c.getScore());
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listaImena) {

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

            }

            @Override
            public void onFailure(Throwable t) {
                Toast t1 = Toast.makeText(getApplicationContext(), "There was an error while loading categories!\n" + t.getMessage(), Toast.LENGTH_SHORT);
                t1.show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainMenu.class);
        this.startActivity(intent);
        finish();
    }
}


