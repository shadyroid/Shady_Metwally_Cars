package com.softxpert.cars.Classes.REST;


import com.softxpert.cars.Classes.REST.Models.Responses.CarsResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class Repository {

    private final ApiInterface apiInterface;

    @Inject
    public Repository(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Observable<CarsResponse> requestCars(int page) {
        return apiInterface.requestCars(page);
    }

}