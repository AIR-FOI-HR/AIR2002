package com.example.trivialy;

<<<<<<< HEAD
=======
import androidx.annotation.RequiresApi;
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
<<<<<<< HEAD
=======
import android.os.Build;
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.responses.GetDataService;
<<<<<<< HEAD
import com.responses.Quiz.AvailableQuizListResponse;
=======
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
import com.responses.RetrofitInstance;
import com.responses.Quiz.*;

import java.time.LocalDateTime;
<<<<<<< HEAD
import java.time.format.DateTimeFormatter;
=======
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

<<<<<<< HEAD
public class AvailableQuizesMenu extends AppCompatActivity {
    int odabranaKategorija = getIntent().getIntExtra("odabranaKategorija", 0);
=======
import static java.time.LocalDateTime.now;

@RequiresApi(api = Build.VERSION_CODES.O)
public class AvailableQuizesMenu extends AppCompatActivity {
    String odabranaKategorija;
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
    private ListView lv;
    ArrayList<Quiz> quizes = new ArrayList<>();
    ArrayList<LocalDateTime> vremenaPocetakaKviza = new ArrayList<>();
    ArrayList<String> imenaKviza = new ArrayList<>();

<<<<<<< HEAD
    UserDataController userDataController;
    UserDataController.UserLives userLives;
=======
    LocalDateTime currentTime = now();

    UserDataController userDataController;
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
    String currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.available_quizes_menu);
        lv =(ListView) findViewById(R.id.listviewAQ);
        userDataController = new UserDataController(getApplicationContext());
<<<<<<< HEAD
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
=======
        currentUser = userDataController.savedUsername;

        Intent i = getIntent();
        odabranaKategorija = (String) i.getSerializableExtra("odabranaKategorija");

        getAvailableQuizes(odabranaKategorija);

        }

    public void getAvailableQuizes(final String categoryId ){
        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        GetAvaliableQuizesRequest request = new GetAvaliableQuizesRequest(categoryId);
        Call<GetAvaliableQuizesResponse> call = getDataService.GetAvaliableQuizes(request);
        call.enqueue(new Callback<GetAvaliableQuizesResponse>() {
            @Override
            public void onResponse(Response<GetAvaliableQuizesResponse> response, Retrofit retrofit) {
                if(!response.isSuccess()){

                }else {
                    if(response.body().getText().equalsIgnoreCase("No avaliable quizes")){ //nema kvizova
                        createQuiz(categoryId);
                    }else if(response.body().getStatus() == Integer.toString(-9)){ //interni error
                        //TODO
                    }else if(response.body().getStatus() == Integer.toString(1)){ //uspješno
                        List<Quiz> quizes = response.body().getQuizList();
                        for (Quiz c : quizes) {
                            vremenaPocetakaKviza.add(c.getStartDate());
                            imenaKviza.add(c.getName());

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
                                    newIntent.putExtra("odabraniKviz", (int) lv.getItemAtPosition(position));
                                    newIntent.putExtra("trenutniKorisnik", (String) currentUser);
                                    GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
                                    AvailableQuizesMenu.this.startActivity(newIntent);
                                    finish();
                                }
                            });

                        }
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void createQuiz(final String categoryId){
        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        CreateQuizRequest request = new CreateQuizRequest(categoryId, "test");
        Call<CreateQuizResponse> call = getDataService.CreateQuiz(request);

        call.enqueue(new Callback<CreateQuizResponse>() {
            @Override
            public void onResponse(Response<CreateQuizResponse> response, Retrofit retrofit) {
                if(!response.isSuccess()){

                    Toast t = Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_SHORT);

                }else{
                    if(response.body().getStatus() == Integer.toString(-9)){ //internal error
                    //todo
                    }else if(response.body().getStatus() == Integer.toString(1)){ //uspješno
                        getAvailableQuizes(categoryId);
                    }
                }
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
            }

            @Override
            public void onFailure(Throwable t) {
<<<<<<< HEAD
                Toast t1 = Toast.makeText(getApplicationContext(), "There was an error while loading available quizes!\n" + t.getMessage(), Toast.LENGTH_SHORT);
                t1.show();
            }
        });
    }
=======

            }
        });

    }

>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
}