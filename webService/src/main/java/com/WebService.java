package com;


import com.responses.WebServiceUserResponse;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface WebService {
    @FormUrlEncoded
    @POST("stores.php")
    Call<WebServiceUserResponse> getUsers(@Field("method") String method);
}
