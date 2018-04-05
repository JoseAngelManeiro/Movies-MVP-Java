package com.joseangelmaneiro.movies.helpers;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;
import android.util.Log;
import com.joseangelmaneiro.movies.platform.MoviesAppTest;


public class CustomTestRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Log.d(this.getClass().getSimpleName(), "Using custom application class for testing.");
        return super.newApplication(cl, MoviesAppTest.class.getName(), context);
    }
}
