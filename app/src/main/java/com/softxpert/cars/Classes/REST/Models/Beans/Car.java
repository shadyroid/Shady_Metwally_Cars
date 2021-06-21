package com.softxpert.cars.Classes.REST.Models.Beans;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Car {
    private int id;
    private String brand;
    private String constractionYear;
    private boolean isUsed;
    private String imageUrl;

}