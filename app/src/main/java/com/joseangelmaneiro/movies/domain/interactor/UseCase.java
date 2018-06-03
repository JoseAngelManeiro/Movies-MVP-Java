package com.joseangelmaneiro.movies.domain.interactor;

import com.joseangelmaneiro.movies.domain.executor.JobScheduler;
import com.joseangelmaneiro.movies.domain.executor.UIScheduler;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;


public abstract class UseCase<Observer, Params> {

    private final UIScheduler uiScheduler;
    private final JobScheduler jobScheduler;

    UseCase(UIScheduler uiScheduler, JobScheduler jobScheduler) {
        this.uiScheduler = uiScheduler;
        this.jobScheduler = jobScheduler;
    }

    abstract Single<Observer> buildUseCaseObservable(Params params);

    public Disposable execute(DisposableSingleObserver<Observer> observer, Params params) {
        final Single<Observer> observable = this.buildUseCaseObservable(params)
                .observeOn(uiScheduler.getScheduler())
                .subscribeOn(jobScheduler.getScheduler());
        return observable.subscribeWith(observer);
    }

}
