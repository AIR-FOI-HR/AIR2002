package com.example.core;

public interface DataLoader {
    void loadData(DataLoaderListener listener, Integer points, Integer numberOfQuestions, String difficultyName, String categoryName);
    void loadMpData(DataLoaderListener listener, Integer points, String questionId, Integer kvizId);
}
