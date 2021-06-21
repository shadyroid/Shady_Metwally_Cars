package com.softxpert.cars.MVVM.$Base;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softxpert.cars.Classes.REST.Repository;
import com.softxpert.cars.Classes.REST.Models.Responses.BaseResponse;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

import io.reactivex.disposables.CompositeDisposable;
import lombok.Getter;
import lombok.Setter;
import timber.log.Timber;

@Setter
@Getter
@HiltViewModel
public class BaseViewModel extends ViewModel {


    @Inject
    Repository repository;
    @Inject
    CompositeDisposable compositeDisposable;
    @Inject
    MutableLiveData<Boolean> onLoadingMutableLiveData;
    @Inject
    MutableLiveData<BaseResponse> onApiErrorMutableLiveData;

    @Inject
    public BaseViewModel() {
    }

    protected boolean validateResponse(BaseResponse response) {
        if (!response.isSuccess()) {
            onApiErrorMutableLiveData.setValue(response);
            Timber.e(new Throwable(response.getMessage()));
            return false;
        } else
            return true;
    }



    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

}
