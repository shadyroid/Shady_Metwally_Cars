package com.softxpert.cars.Classes.Others;


import android.app.Application;


import com.softxpert.cars.BuildConfig;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;
import timber.log.Timber;

@HiltAndroidApp
public class MainApplication extends Application {

    @Inject
    Timber.DebugTree debugTree;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(debugTree);
        }
    }
}
