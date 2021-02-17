package com.example.trivialy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import com.responses.GetDataService;
import com.responses.Quiz.GetAvailableQuizesRequest;
import com.responses.Quiz.GetAvailableQuizesResponse;
import com.responses.Quiz.GetUsersOnQuizResponse;
import com.responses.Quiz.Quiz;
import com.responses.RetrofitInstance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static java.time.LocalDateTime.now;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Lobby extends AppCompatActivity {
    int idKviza = getIntent().getIntExtra("odabraniKviz", 0);
    String odabranaKategorija = getIntent().getStringExtra("odabranaKategorija");
    List<Quiz> listaKvizova;
    LocalDateTime vrijemePocetkaKviza;
    LocalDateTime  currentTime = now();
    private ListView lv;

    UserDataController userDataController;
    UserDataController.UserLives userLives;
    String currentUser = userLives.Username;
    GetUsersOnQuizResponse odgovor;
    ArrayList<String> usersOnQuiz = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);


        do {
            GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
            final Call<GetUsersOnQuizResponse> call = getDataService.getUsersOnQuiz(idKviza);

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
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast t1 = Toast.makeText(getApplicationContext(), "There was an error while loading users on quiz!\n" + t.getMessage(), Toast.LENGTH_SHORT);
                    t1.show();
                }
            });

        }while(currentTime.isBefore(vrijemePocetkaKviza));
    }

    /*public LocalDateTime getDateTimeOfTheQuiz(){
        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        GetAvailableQuizesRequest request = new GetAvailableQuizesRequest(odabranaKategorija);
        final Call<GetAvailableQuizesResponse> call = getDataService.GetAvailableQuizes(request);

        call.enqueue(new Callback<GetAvailableQuizesResponse>() {
            @Override
            public void onResponse(Response<List<Quiz>> response, Retrofit retrofit) {
                listaKvizova = (List<Quiz>) response.body();
                for(Quiz q : listaKvizova){
                    if(q.getQuizID() == idKviza){
                        vrijemePocetkaKviza = q.getStartDate();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast t1 = Toast.makeText(getApplicationContext(), "There was an error while loading users on quiz!\n" + t.getMessage(), Toast.LENGTH_SHORT);
                t1.show();
            }
        });
        return vrijemePocetkaKviza;
    }*/
}