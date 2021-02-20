package com.example.core;


import com.responses.QuestionsHandler.QuestionsListResponse;

import java.util.List;

public interface DataLoaderListener {
    void onDataLoaded(String status, String text, DataLoaderListener listener, List<QuestionsListResponse> questions, Integer points, String difficultyName, String categoryName);
    void onMpDataLoaded(String status, String text, DataLoaderListener listener, List<QuestionsListResponse> questions, Integer points, Integer kvizId);
}
