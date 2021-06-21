package com.softxpert.cars.MVVM.$Base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;

import com.softxpert.cars.Classes.Dialogs.ErrorHandlerDialog;
import com.softxpert.cars.Classes.REST.Models.Responses.BaseResponse;
import com.softxpert.cars.Classes.UI.LoadingProgressView;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BaseActivity extends AppCompatActivity {


    @Inject
    LoadingProgressView progressView;
    @Inject
    ErrorHandlerDialog errorHandlerDialog;


    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = this.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void onLoading(boolean isLoading) {
        hideKeyboard();
        progressView.isShow(isLoading);
    }

    public void onApiError(BaseResponse response) {
        errorHandlerDialog.setBaseResponse(response);
        errorHandlerDialog.show();
    }




    public void onBackClick(View view) {
        finish();
    }




}
