package com.softxpert.cars.Classes.Dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;


import com.softxpert.cars.Classes.REST.Models.Responses.BaseResponse;
import com.softxpert.cars.R;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ActivityContext;


public class ErrorHandlerDialog {

    Context context;
    AlertDialog errorAlertDialog;

    @Inject
    public ErrorHandlerDialog(@ActivityContext Context context, AlertDialog errorAlertDialog) {
        this.context = context;
        this.errorAlertDialog = errorAlertDialog;

        errorAlertDialog.setTitle(context.getString(R.string.error));
        errorAlertDialog.setCancelable(false);
    }

    public void setBaseResponse(BaseResponse response) {
        if (response.getStatus() == 503) {
            errorAlertDialog.setMessage(context.getString(R.string.please_check_your_internet_connection));
            errorAlertDialog.setButton(DialogInterface.BUTTON_POSITIVE, context.getResources().getString(R.string.ok), (dialog, which) -> errorAlertDialog.dismiss());
        }  else {
            errorAlertDialog.setMessage(response.getMessage());
            errorAlertDialog.setButton(DialogInterface.BUTTON_POSITIVE, context.getResources().getString(R.string.ok), (dialog, which) -> errorAlertDialog.dismiss());
        }

    }

    public void show() {
        errorAlertDialog.show();
    }
}
