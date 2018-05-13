/*
 *   Copyright 2016 Fabio Collini.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.joseangelmaneiro.movies;
import android.support.test.InstrumentationRegistry;
import com.joseangelmaneiro.movies.platform.MoviesApp;
import com.joseangelmaneiro.movies.platform.di.app.AppComponent;
import com.joseangelmaneiro.movies.platform.di.app.AppModule;
import it.cosenonjaviste.daggermock.DaggerMockRule;

public class EspressoDaggerMockRule extends DaggerMockRule<AppComponent> {

    public EspressoDaggerMockRule() {
        super(AppComponent.class, new AppModule());

        customizeBuilder(
                (BuilderCustomizer<AppComponent.Builder>) builder -> builder.application(getApp()));

        set(component -> component.inject(getApp()));
    }

    private static MoviesApp getApp() {
        return (MoviesApp) InstrumentationRegistry
                .getInstrumentation()
                .getTargetContext()
                .getApplicationContext();
    }

}