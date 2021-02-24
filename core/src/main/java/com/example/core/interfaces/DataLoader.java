package com.example.core.interfaces;

public interface DataLoader {
    void loadData(DataLoaderListener listener, Integer points, Integer numberOfQuestions, String difficultyName, String categoryName, Integer kvizId);
    void loadMpData(DataLoaderListener listener, Integer points, String questionId, Integer kvizId);
}
