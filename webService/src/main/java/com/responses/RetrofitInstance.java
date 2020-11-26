package com.responses;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://localhost:8081/api/";

    /**
     * Create an instance of Retrofit object
     * */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
