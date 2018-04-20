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
    private final CompositeDisposable disposables;

    UseCase(UIScheduler uiScheduler, JobScheduler jobScheduler) {
        this.uiScheduler = uiScheduler;
        this.jobScheduler = jobScheduler;
        this.disposables = new CompositeDisposable();
    }

    abstract Single<Observer> buildUseCaseObservable(Params params);

    public void execute(DisposableSingleObserver<Observer> observer, Params params) {
        final Single<Observer> observable = this.buildUseCaseObservable(params)
                .observeOn(uiScheduler.getScheduler())
                .subscribeOn(jobScheduler.getScheduler());
        addDisposable(observable.subscribeWith(observer));
    }

    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    private void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

}
