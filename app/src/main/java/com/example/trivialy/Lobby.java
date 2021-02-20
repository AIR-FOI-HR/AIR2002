package com.example.trivialy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;

import com.example.trivialy.Gamemodes.MultiplayerPlay;
import com.responses.GetDataService;
import com.responses.QuestionsHandler.QuestionsListResponse;
import com.responses.Quiz.GetUsersOnQuizRequest;
import com.responses.Quiz.GetUsersOnQuizResponse;
import com.responses.Quiz.Quiz;
import com.responses.Quiz.SetUserToQuizRequest;
import com.responses.Quiz.SetUserToQuizResponse;
import com.responses.RetrofitInstance;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static java.time.LocalDateTime.now;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Lobby extends AppCompatActivity {
    int idKviza;
    String odabranaKategorija;
    List<Quiz> listaKvizova;
    String vrijeme;
    String kvizIds;
    LocalDateTime vrijemePocetkaKviza;
    LocalDateTime  currentTime;
    private ListView lv;
    private Integer counter;
    List<QuestionsListResponse> pitanja;

    Integer Points;

    QuestionsListResponse question;

    UserDataController userDataController;
    UserDataController.UserLives userLives;
    String currentUser;
    GetUsersOnQuizResponse odgovor;
    ArrayList<String> usersOnQuiz = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        idKviza = getIntent().getIntExtra("odabraniKviz", 0);
        odabranaKategorija = getIntent().getStringExtra("odabranaKategorija");
        vrijeme = getIntent().getStringExtra("vrijemeKviza");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.M.yyyy. H:mm:ss");
        vrijemePocetkaKviza = LocalDateTime.parse(vrijeme, dateFormat);

        userDataController = new UserDataController(this);
        userLives = userDataController.GetUserData();

        kvizIds = getIntent().getStringExtra("parametri");
        counter = 0;

        pitanja = new ArrayList<>();

        currentUser = userLives.Username;
        lv = findViewById(R.id.userList);

        currentTime = now();
        long minutes = currentTime.until(vrijemePocetkaKviza, ChronoUnit.MINUTES);
        long seconds = currentTime.until(vrijemePocetkaKviza, ChronoUnit.SECONDS);
        long diff = (minutes * 60) + seconds;

        setToQuiz(idKviza, currentUser, 0);

        MyCount count = new MyCount(diff * 1000, 1000);
        count.start();
    }

    private void setToQuiz(int idKviza, String currentUser, int i) {
        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        SetUserToQuizRequest request = new SetUserToQuizRequest(idKviza,currentUser, i);
        Call<SetUserToQuizResponse> call = getDataService.SetUserToQuiz(request);

        call.enqueue(new Callback<SetUserToQuizResponse>() {
            @Override
            public void onResponse(Response<SetUserToQuizResponse> response, Retrofit retrofit) {
                if (!response.isSuccess()) {

                } else {
                    if (response.body().getStatus().equals(Integer.toString(-1))) { //nije uspjelo
                        Toast t = Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT);
                        t.show();
                    }
                }
            }
            @Override
            public void onFailure (Throwable t){
                Toast to = Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT);
                to.show();
            }
        });
    }

    private void waitInLobby() {
        currentTime = now();
        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        GetUsersOnQuizRequest request = new GetUsersOnQuizRequest(idKviza);
        final Call<GetUsersOnQuizResponse> call = getDataService.getUsersOnQuiz(request);
        call.enqueue(new Callback<GetUsersOnQuizResponse>() {
            @Override
            public void onResponse(Response<GetUsersOnQuizResponse> response, Retrofit retrofit) {
                odgovor = (GetUsersOnQuizResponse) response.body();
                usersOnQuiz = odgovor.getUsernames();
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, usersOnQuiz) {

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
                Toast t1 = Toast.makeText(getApplicationContext(), "There was an error while loading users on quiz!\n" + t.getMessage(), Toast.LENGTH_SHORT);
                t1.show();
            }
        });
    }

    public class MyCount extends CountDownTimer {
        MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            waitInLobby();
        }

        @Override
        public void onFinish() {
            playGame();
        }
    }

    private void playGame() {
        Intent newIntent = new Intent(getApplicationContext(), MultiplayerPlay.class);
        newIntent.putExtra("kvizIds", kvizIds);
        newIntent.putExtra("kvizId", idKviza);
        startActivity(newIntent);
        finish();
    }
}