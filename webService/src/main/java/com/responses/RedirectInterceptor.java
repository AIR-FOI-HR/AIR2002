package com.responses;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class RedirectInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(chain.request());
        if (response.code() == 307) {
            request = request.newBuilder()
                    .url(response.header("Location"))
                    .build();
            response = chain.proceed(request);
        }
        return response;
    }
}
