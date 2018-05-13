package com.joseangelmaneiro.movies.platform.views;

import android.support.v7.app.AlertDialog;
import com.joseangelmaneiro.movies.R;
import com.joseangelmaneiro.movies.presentation.BaseView;
import dagger.android.support.DaggerAppCompatActivity;


public abstract class BaseActivity extends DaggerAppCompatActivity implements BaseView {

    @Override
    public void showErrorMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this,
                R.style.Theme_AppCompat_Light_Dialog_Alert);
        builder.setMessage(message);
        builder.setPositiveButton(android.R.string.ok, (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
