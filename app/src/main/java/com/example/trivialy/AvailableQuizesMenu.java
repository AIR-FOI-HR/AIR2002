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

import static java.time.LocalDateTime.now;

@RequiresApi(api = Build.VERSION_CODES.O)
public class AvailableQuizesMenu extends AppCompatActivity {
    int odabranaKategorija;
    private ListView lv;
    ArrayList<Quiz> quizes = new ArrayList<>();
    ArrayList<LocalDateTime> vremenaPocetakaKviza = new ArrayList<>();
    ArrayList<String> imenaKviza = new ArrayList<>();

    LocalDateTime currentTime = now();

    UserDataController userDataController;
    String currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.available_quizes_menu);
        lv =(ListView) findViewById(R.id.listviewAQ);
        userDataController = new UserDataController(getApplicationContext());
        currentUser = userDataController.savedUsername;

        Intent i = getIntent();
        odabranaKategorija = (int) i.getSerializableExtra("odabranaKategorija");

        getAvailableQuizes(odabranaKategorija);

        }

    public void getAvailableQuizes(final int categoryId ){
        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        GetAvailableQuizesRequest request = new GetAvailableQuizesRequest(categoryId);
        Call<GetAvailableQuizesResponse> call = getDataService.GetAvaliableQuizes(request);
        call.enqueue(new Callback<GetAvailableQuizesResponse>() {
            @Override
            public void onResponse(Response<GetAvailableQuizesResponse> response, Retrofit retrofit) {
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

    public void createQuiz(final int categoryId){
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
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

}