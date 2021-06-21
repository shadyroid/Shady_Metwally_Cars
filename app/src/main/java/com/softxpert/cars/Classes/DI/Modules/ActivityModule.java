package com.softxpert.cars.Classes.DI.Modules;


import android.content.Context;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;

import net.bohush.geometricprogressview.GeometricProgressView;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ActivityContext;

@Module
@InstallIn(ActivityComponent.class)
public class ActivityModule {

    @Provides
    public GeometricProgressView progressView(@ActivityContext Context context) {
        return new GeometricProgressView(context);
    }
    @Provides
    public AlertDialog alertDialog(@ActivityContext Context context) {
        return new AlertDialog.Builder(context).create();
    }

    @Provides
    public LinearLayoutManager linearLayoutManager(@ActivityContext Context context) {
        return new LinearLayoutManager(context);

    }


}
