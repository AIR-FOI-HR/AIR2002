package com.example.core.loader;

import android.content.Context;
import android.widget.Toast;


import androidx.fragment.app.FragmentTransaction;

import com.example.core.DataLoader;
import com.example.core.DataLoaderListener;
import com.responses.GetDataService;
import com.responses.QuestionsHandler.Question;
import com.responses.QuestionsHandler.QuestionByIdRequest;
import com.responses.QuestionsHandler.QuestionRequest;
import com.responses.QuestionsHandler.QuestionsByIdResponse;
import com.responses.QuestionsHandler.QuestionsListResponse;
import com.responses.QuestionsHandler.QuestionsResponse;
import com.responses.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;



public class WebServiceDataLoader implements DataLoader {
    private Context context;
    public WebServiceDataLoader(Context context){
        this.context = context;
    }
    @Override
    public void loadData(final DataLoaderListener listener, final Integer points , Integer numberOfQuestions, String difficultyName, String categoryName) {
        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        QuestionRequest questionRequest = new QuestionRequest(numberOfQuestions, difficultyName, categoryName);
        Call<QuestionsResponse> call = getDataService.GetQuestionsByCategoryAndDifficulty(questionRequest);
        call.enqueue(new Callback<QuestionsResponse>() {
            @Override
            public void onResponse(Response<QuestionsResponse> response, Retrofit retrofit) {
                if (!response.isSuccess()){
                    Toast t = Toast.makeText(context, String.valueOf(response.code()), Toast.LENGTH_SHORT);
                    t.show();
                    return;
                }else{
                    if (response.body().getStatus().equals(Integer.toString(-1))){
                        Toast t = Toast.makeText(context, response.body().getText(), Toast.LENGTH_SHORT);
                        t.show();
                    }else if(response.body().getStatus().equals(Integer.toString(-9)))
                    {
                        Toast t = Toast.makeText(context, response.body().getText(), Toast.LENGTH_SHORT);
                        t.show();
                    }
                    else if(response.body().getStatus().equals(Integer.toString(1))){
                        listener.onDataLoaded(response.body().getStatus(), response.body().getText(), listener, response.body().getQuestions(), points, difficultyName, categoryName);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast t1 = Toast.makeText(context, "There was an error while loading questions!\n" + t.getMessage(), Toast.LENGTH_SHORT);
                t1.show();
            }
        });
    }

    @Override
    public void loadMpData(DataLoaderListener listener, Integer points, String questionId, Integer kvizId) {
        Integer idPitanja = Integer.valueOf(questionId);
        GetDataService getDataService = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        QuestionByIdRequest request = new QuestionByIdRequest(idPitanja);
        Call<QuestionsByIdResponse> call = getDataService.GetQuestionsById(request);
        call.enqueue(new Callback<QuestionsByIdResponse>() {
            @Override
            public void onResponse(Response<QuestionsByIdResponse> response, Retrofit retrofit) {
                boolean test = response.isSuccess();
                List<QuestionsListResponse> tempList = new ArrayList<>();
                tempList.add(response.body().getQuestions());
                listener.onMpDataLoaded(response.body().getStatus().toString(), response.body().getText(), listener, tempList, points, kvizId);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
