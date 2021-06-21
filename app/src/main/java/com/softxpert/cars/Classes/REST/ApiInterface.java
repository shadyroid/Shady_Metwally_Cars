package com.softxpert.cars.Classes.REST;


import com.softxpert.cars.Classes.REST.Models.Responses.CarsResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("cars")
    Observable<CarsResponse> requestCars(@Query("page") int page);

}