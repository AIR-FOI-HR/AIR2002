package com.example.trivialy.loader;

import com.example.core.DataLoaderListener;
import com.responses.GetDataService;
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

public class MultiplayerDataLoader {
    public void LoadData(final DataLoaderListener listener,  final Integer points, String questionId){
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
                    listener.onDataLoaded(response.body().getStatus().toString(), response.body().getText(), tempList, points);
                }

                @Override
                public void onFailure(Throwable t) {
                    String bezvezr = "asdfa";
                }
            });
    }
}
