package com.example.trivialy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.responses.GetDataService;
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

import static java.time.LocalDateTime.now;

@RequiresApi(api = Build.VERSION_CODES.O)
public class AvailableQuizesMenu extends AppCompatActivity {
    String odabranaKategorija;
    private ListView lv;
    ArrayList<Quiz> quizes = new ArrayList<>();
    ArrayList<String> vremenaPocetakaKviza = new ArrayList<>();
    ArrayList<String> imenaKviza = new ArrayList<>();

    LocalDateTime currentTime = now();

    UserDataController userDataController;
    String currentUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.available_quizes_menu);
        lv = (ListView) findViewById(R.id.listviewAQ);
        userDataController = new UserDataController(getApplicationContext());
        UserDataController.UserLives ul = userDataController.GetUserData();
        currentUser = ul.Username;

        Intent i = getIntent();
        odabranaKategorija = (String) i.getSerializableExtra("odabranaKategorija");

        new CountDownTimer(60 * 60 * 1000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                imenaKviza.clear();
                getAvailableQuizes(odabranaKategorija);
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    public void getAvailableQuizes(final String categoryId) {
        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        GetAvaliableQuizesRequest request = new GetAvaliableQuizesRequest(Integer.valueOf(categoryId));
        Call<GetAvaliableQuizesResponse> call = getDataService.GetAvaliableQuizes(request);
        call.enqueue(new Callback<GetAvaliableQuizesResponse>() {
            @Override
            public void onResponse(Response<GetAvaliableQuizesResponse> response, Retrofit retrofit) {
                boolean test = response.isSuccess();
                if (!response.isSuccess()) {

                } else {
                    if (response.body().getText().equalsIgnoreCase("No avaliable quizes")) { //nema kvizova
                        createQuiz(categoryId);
                    } else if (response.body().getStatus().equals(Integer.toString(-9))) { //interni error
                        //TODO
                    } else if (response.body().getStatus().equals(Integer.toString(1))) { //uspješno
                        final List<Quiz> quizes = response.body().getQuizList();
                        for (Quiz c : quizes) {
                            vremenaPocetakaKviza.add(c.getStartDate().toString());

                            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.M.yyyy. H:mm:ss");
                            LocalDateTime vrijemePocetkaKviza = LocalDateTime.parse(c.getStartDate(), dateFormat);

                            if(now().isBefore(vrijemePocetkaKviza)){
                                imenaKviza.add(c.getName());
                            }
                        }

                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                                android.R.layout.simple_list_item_1, imenaKviza) {

                            @Override
                            public View getView(int position, View convertView, ViewGroup parent) {
                                View view = super.getView(position, convertView, parent);
                                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                                tv.setTextColor(Color.BLACK);
                                return view;
                            }
                        };

                        lv.setAdapter(arrayAdapter);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent newIntent = new Intent(getApplicationContext(), Lobby.class);
                                String kvizIme = (String) lv.getItemAtPosition(position);
                                Quiz kvizTemp = new Quiz();
                                for (Quiz c: quizes) {
                                    if(c.getName().equals(kvizIme)){
                                        kvizTemp = c;
                                    }
                                }
                                newIntent.putExtra("odabraniKviz", kvizTemp.getQuizID());
                                newIntent.putExtra("trenutniKorisnik", (String) currentUser);
                                newIntent.putExtra("vrijemeKviza", kvizTemp.getStartDate());
                                newIntent.putExtra("parametri", kvizTemp.getQuestionsIds());
                                GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
                                AvailableQuizesMenu.this.startActivity(newIntent);
                                finish();
                            }
                        });

                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.i("onFailure", "test");
            }
        });
    }

    public void createQuiz(final String categoryId) {
        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        CreateQuizRequest request = new CreateQuizRequest(Integer.valueOf(categoryId), currentUser + "s quiz");
        Call<CreateQuizResponse> call = getDataService.CreateQuiz(request);

        call.enqueue(new Callback<CreateQuizResponse>() {
            @Override
            public void onResponse(Response<CreateQuizResponse> response, Retrofit retrofit) {
                if (!response.isSuccess()) {

                } else {
                    if (response.body().getStatus().equals(Integer.toString(-9))) { //internal error
                        //todo
                    } else if (response.body().getStatus().equals(Integer.toString(1))) { //uspješno
                        getAvailableQuizes(categoryId);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

}