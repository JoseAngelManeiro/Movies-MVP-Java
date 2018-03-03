package com.joseangelmaneiro.movies.platform.views;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import com.joseangelmaneiro.movies.R;
import com.joseangelmaneiro.movies.presentation.BaseView;


@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    public void showErrorMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this,
                R.style.Theme_AppCompat_Light_Dialog_Alert);
        builder.setMessage(message);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
