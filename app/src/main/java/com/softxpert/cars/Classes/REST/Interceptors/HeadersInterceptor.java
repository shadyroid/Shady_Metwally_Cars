package com.softxpert.cars.Classes.REST.Interceptors;


import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class HeadersInterceptor implements Interceptor {


    @Inject
    public HeadersInterceptor( ) {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder requestBuilder = request.newBuilder();

        requestBuilder.addHeader("Accept", "application/json");

        return chain.proceed(requestBuilder.build());
    }


}
