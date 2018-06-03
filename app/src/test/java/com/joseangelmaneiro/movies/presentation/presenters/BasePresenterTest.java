package com.joseangelmaneiro.movies.presentation.presenters;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class BasePresenterTest {

    BasePresenter sut;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        sut = new BasePresenter() {
            @Override
            void addDisposable(Disposable disposable) {
                super.addDisposable(disposable);
            }

            @Override
            public void destroy() {
                super.destroy();
            }
        };

    }

    @Test
    public void addDisposableIncreasesTheNumberOfDisposables(){
        givenThreeDisposables();

        checkIfTheSizeIsCorrect();
    }

    private void givenThreeDisposables(){
        sut.addDisposable(Mockito.mock(Disposable.class));
        sut.addDisposable(Mockito.mock(Disposable.class));
        sut.addDisposable(Mockito.mock(Disposable.class));
    }

    private void checkIfTheSizeIsCorrect(){
        CompositeDisposable compositeDisposable = sut.getCompositeDisposable();
        assertThat(compositeDisposable.size(), is(3));
    }

}