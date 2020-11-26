package com.example.trivialy;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.responses.Category;
import com.responses.GetDataService;
import com.responses.RetrofitInstance;

import java.util.List;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class CategoryView extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_listview);

        textView = (TextView)findViewById(R.id.text_view_result);

        //Poziva se GetDataService interface kako bi joj proslijedili retrofit instancu koja sadrži konekciju prema servisu
        //Interface GetDataService sadrži sve metode prema servisu koje nam trebaju
        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);

        Call<List<Category>> call = getDataService.getCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Response<List<Category>> response, Retrofit retrofit) {
            if (!response.isSuccess()){
                textView.setText("Code: " + response.code());
                return;
            }else{
                List<Category> categories =  response.body();
                String contents="";
                for(Category category: categories){
                    contents +="ID: "+ category.getId() + "   " + "Name: "+ category.getTitle() + "\n";
                }
                textView.append(contents);
            }
            }
            @Override
            public void onFailure(Throwable t) {
            textView.setText(t.getMessage());
            }
        });
    }
}
