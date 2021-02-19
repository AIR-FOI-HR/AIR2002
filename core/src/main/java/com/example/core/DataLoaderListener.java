package com.example.core;


import com.responses.QuestionsHandler.QuestionsListResponse;

import java.util.List;

public interface DataLoaderListener {
    void onDataLoaded(String status, String text, List<QuestionsListResponse> questions, Integer points);
}
