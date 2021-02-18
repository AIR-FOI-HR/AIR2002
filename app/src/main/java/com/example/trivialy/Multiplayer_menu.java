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

import com.responses.Category;
import com.responses.GetDataService;
import com.responses.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

<<<<<<< HEAD
=======
import static java.lang.Integer.parseInt;

>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
public class Multiplayer_menu extends AppCompatActivity {
    private ListView lv;
    ArrayList<Category> categories = new ArrayList<>();
    ArrayList<String> listaImena = new ArrayList<>();
<<<<<<< HEAD
=======
    String odabranaKategorijaId;
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplayer_menu);
        lv = (ListView) findViewById(R.id.listViewMm);

        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        final Call<List<Category>> call = getDataService.getCategories();

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Response<List<Category>> response, Retrofit retrofit) {
                categories = (ArrayList<Category>) response.body();
                for (Category c : categories) {
                    listaImena.add(c.getTitle());
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listaImena) {

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        // Get the Item from ListView
                        View view = super.getView(position, convertView, parent);
                        // Initialize a TextView for ListView each Item
<<<<<<< HEAD
                        TextView tv = (TextView) view.findViewById(android.R.id.text1); //DA LI JE OVO OK?

                        // Set the text color of TextView (ListView Item)
                        tv.setTextColor(Color.WHITE);
=======
                        TextView tv = (TextView) view.findViewById(android.R.id.text1);

                        // Set the text color of TextView (ListView Item)
                        tv.setTextColor(Color.BLACK);
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf

                        // Generate ListView Item using TextView
                        return view;
                    }
                };

<<<<<<< HEAD
=======

>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
                lv.setAdapter(arrayAdapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent newIntent = new Intent(getApplicationContext(), AvailableQuizesMenu.class);
<<<<<<< HEAD
                        newIntent.putExtra("odabranaKategorija", (String) lv.getItemAtPosition(position));
=======
                        String nazivOdabraneKategorije = (String) lv.getItemAtPosition(position);

                        for (Category c : categories){
                            if(c.getTitle().equalsIgnoreCase(nazivOdabraneKategorije) && parseInt(c.getId())<26){
                                odabranaKategorijaId = c.getId();
                            }
                        }
                        newIntent.putExtra("odabranaKategorija", odabranaKategorijaId);
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
                        Multiplayer_menu.this.startActivity(newIntent);
                        finish();

                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {
                Toast t1 = Toast.makeText(getApplicationContext(), "There was an error while loading categories!\n" + t.getMessage(), Toast.LENGTH_SHORT);
                t1.show();
            }
        });
    }
}