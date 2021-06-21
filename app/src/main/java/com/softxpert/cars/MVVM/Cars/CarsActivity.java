package com.softxpert.cars.MVVM.Cars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.softxpert.cars.Classes.Adapters.CarsAdapter;
import com.softxpert.cars.Classes.REST.Models.Responses.CarsResponse;
import com.softxpert.cars.Classes.UI.EndlessRecyclerViewScrollListener;
import com.softxpert.cars.MVVM.$Base.BaseActivity;
import com.softxpert.cars.R;
import com.softxpert.cars.databinding.ActivityCarsBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@AndroidEntryPoint
public class CarsActivity extends BaseActivity {
    ActivityCarsBinding mBinding;
    CarsViewModel mViewModel;

    @Inject
    CarsAdapter carsAdapter;
    @Inject
    LinearLayoutManager linearLayoutManager;

    EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityCarsBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mViewModel = new ViewModelProvider(this).get(CarsViewModel.class);
        mViewModel.getOnLoadingMutableLiveData().observe(this, this::onLoading);
        mViewModel.getOnApiErrorMutableLiveData().observe(this, this::onApiError);
        mViewModel.getCarsResponseMutableLiveData().observe(this, this::onCarsResponse);



        endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                requestCars(page);
            }
        };

        mBinding.rvCars.setAdapter(carsAdapter);
        mBinding.rvCars.setLayoutManager(linearLayoutManager);
        mBinding.rvCars.addOnScrollListener(endlessRecyclerViewScrollListener);


        mBinding.swipeRefreshLayout.setOnRefreshListener(() -> {
            carsAdapter.clear();
            endlessRecyclerViewScrollListener.resetState();
            requestCars(1);
            mBinding.swipeRefreshLayout.setRefreshing(false);
        });


        requestCars(1);

    }

    private void requestCars(int page) {
        mViewModel.requestCars(page);
    }

    private void onCarsResponse(CarsResponse response) {
        Timber.d("onCarsResponse: "+response.getData().toString());
        carsAdapter.setData(response.getData());
    }


  
}