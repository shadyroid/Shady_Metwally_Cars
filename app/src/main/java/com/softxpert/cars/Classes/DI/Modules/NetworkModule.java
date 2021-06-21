package com.softxpert.cars.Classes.DI.Modules;


import androidx.lifecycle.MutableLiveData;

import com.softxpert.cars.Classes.Others.CONSTANTS;
import com.softxpert.cars.Classes.REST.ApiInterface;
import com.softxpert.cars.Classes.REST.Interceptors.HeadersInterceptor;
import com.softxpert.cars.Classes.REST.Interceptors.NetworkInterceptor;
import com.softxpert.cars.Classes.REST.Models.Responses.BaseResponse;
import com.softxpert.cars.Classes.REST.Models.Responses.CarsResponse;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Provides
    @Singleton
    public ApiInterface provideApiInterface(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(CONSTANTS.BACKEND_CONSTANTS.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
                .create(ApiInterface.class);

    }


    @Provides
    @Singleton
    public OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, HeadersInterceptor headersInterceptor, NetworkInterceptor networkInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(networkInterceptor)
                .addInterceptor(headersInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build();

    }


    @Provides
    @Singleton
    public HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> Timber.i(message));
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Provides
    public CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }


    @Provides
    public MutableLiveData<Boolean> booleanMutableLiveData() {
        return new MutableLiveData<>();
    }


    @Provides
    public MutableLiveData<BaseResponse> baseResponseMutableLiveData() {
        return new MutableLiveData<>();
    }
    @Provides
    public MutableLiveData<CarsResponse> carsResponseMutableLiveData() {
        return new MutableLiveData<>();
    }


}
