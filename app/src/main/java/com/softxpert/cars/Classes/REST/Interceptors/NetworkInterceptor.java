package com.softxpert.cars.Classes.REST.Interceptors;

import com.google.gson.Gson;
import com.softxpert.cars.Classes.REST.Models.Responses.BaseResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import timber.log.Timber;


@Singleton
public class NetworkInterceptor implements Interceptor {


    @Inject
    public NetworkInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) {
        Gson gson = new Gson();
        Request request = chain.request();

        if (!isInternetAvailable()) {
            return new Response.Builder()
                    .code(200) // Whatever code
                    .body(ResponseBody.create(gson.toJson(BaseResponse.builder().status(503).success(false).build()), null)) // Whatever body
                    .protocol(Protocol.HTTP_2)
                    .message("")
                    .request(request)
                    .build();
        } else {

            try {

                Response response = chain.proceed(request);
                ResponseBody body = response.body();
                String bodyString = body.string();
                MediaType contentType = body.contentType();

                //If it's JSON response
                if (isValidJSON(bodyString) && response.code() != 200) {
                    BaseResponse baseResponse = gson.fromJson(bodyString, BaseResponse.class);
                    baseResponse.setStatus(response.code());
                    String editedResponse = gson.toJson(baseResponse);
                    Timber.d("intercept: %s", editedResponse);
                    return response.newBuilder().body(ResponseBody.create(editedResponse, contentType)).code(200).build();
                } else {
                    return response.newBuilder().body(ResponseBody.create(bodyString, contentType)).build();
                }
            } catch (IOException e) {
                Timber.e(e);
            }
        }
        return null;
    }


    public boolean isValidJSON(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(json);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    public boolean isInternetAvailable() {
        try {
            int timeoutMs = 1500;
            Socket sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);

            sock.connect(sockaddr, timeoutMs);
            sock.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }


}