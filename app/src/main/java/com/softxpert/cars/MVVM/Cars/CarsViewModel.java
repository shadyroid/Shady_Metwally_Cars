package com.softxpert.cars.MVVM.Cars;


import androidx.lifecycle.MutableLiveData;

import com.softxpert.cars.Classes.REST.Models.Responses.CarsResponse;
import com.softxpert.cars.MVVM.$Base.BaseViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lombok.Getter;
import lombok.Setter;
import timber.log.Timber;

@Setter
@Getter
@HiltViewModel
public class CarsViewModel extends BaseViewModel {



    @Inject
    MutableLiveData<CarsResponse> carsResponseMutableLiveData;

    @Inject
    public CarsViewModel(){}

    public MutableLiveData<CarsResponse> requestCars(int page) {
        getCompositeDisposable().add(Observable.just(page)
                .doOnNext(__ -> getOnLoadingMutableLiveData().setValue(true))
                .observeOn(Schedulers.io())
                .flatMap(data -> getRepository().requestCars(data))
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> getOnLoadingMutableLiveData().setValue(false))
                .takeWhile(this::validateResponse)
                .subscribe(response -> carsResponseMutableLiveData.setValue(response), Timber::e));
        return carsResponseMutableLiveData;
    }


}
