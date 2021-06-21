package com.softxpert.cars.Classes.REST.Models.Responses;


import com.softxpert.cars.Classes.REST.Models.Beans.Car;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CarsResponse extends BaseResponse {
    private List<Car> data;
}