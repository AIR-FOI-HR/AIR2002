package com.example.core.interfaces;


import com.responses.QuestionsHandler.QuestionsListResponse;

import java.util.List;

public interface DataLoaderListener {
    void onDataLoaded(String status, String text, DataLoaderListener listener, List<QuestionsListResponse> questions, Integer points, String difficultyName, String categoryName, Integer kvizId);
}
