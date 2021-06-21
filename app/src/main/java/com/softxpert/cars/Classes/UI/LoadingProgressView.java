package com.softxpert.cars.Classes.UI;

import android.app.Dialog;
import android.content.Context;
import android.view.WindowManager;
import android.widget.LinearLayout;


import com.softxpert.cars.R;

import net.bohush.geometricprogressview.GeometricProgressView;
import net.bohush.geometricprogressview.TYPE;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ActivityContext;

public class LoadingProgressView extends Dialog {

    @Inject
    public LoadingProgressView(@ActivityContext Context context, GeometricProgressView progressView) {
        super(context);

        setCancelable(false);
        progressView.setType(TYPE.KITE);
        progressView.setNumberOfAngles(30);
        progressView.setFigurePaddingInDp(1);
        progressView.setColor(context.getResources().getColor(R.color.colorPrimary));
        progressView.setDuration(1000);

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        setContentView(progressView);
    }


    public void isShow(boolean isShow) {
        if (isShow)
            show();
        else
            dismiss();

    }
}
