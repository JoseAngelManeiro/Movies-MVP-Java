package com.joseangelmaneiro.movies.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import com.joseangelmaneiro.movies.R;


public class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    public void showErrorMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this,
                R.style.Theme_AppCompat_Light_Dialog_Alert);
        builder.setMessage(getString(R.string.error_has_ocurred));
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
