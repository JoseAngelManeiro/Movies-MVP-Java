package com.joseangelmaneiro.movies.ui.list;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.joseangelmaneiro.movies.R;
import com.joseangelmaneiro.movies.di.Injection;
import com.joseangelmaneiro.movies.ui.Formatter;


public class MovieListActivity extends AppCompatActivity implements MovieListView {

    private MovieListPresenter presenter;

    private CoordinatorLayout coordinatorLayout;

    private MoviesAdapter adapter;

    private SwipeRefreshLayout refreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        setUpPresenter();

        setUpRootView();

        setUpActionBar();

        setUpListView();

        setUpRefreshView();

        informPresenterViewIsReady();

    }

    private void setUpPresenter(){
        presenter = new MovieListPresenter(
                Injection.provideRepository(getApplicationContext()),
                new Formatter());
        presenter.setView(this);
    }

    private void setUpRootView(){
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
    }

    private void setUpActionBar(){
        Toolbar toolbar =  (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setUpListView(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new MoviesAdapter(presenter);
        recyclerView.setAdapter(adapter);
    }

    private void setUpRefreshView(){
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.refreshLayout);
        refreshLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorAccent);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refresh();
            }
        });
    }

    private void informPresenterViewIsReady(){
        presenter.viewReady();
    }

    @Override
    public void refreshList() {
        adapter.refreshData();
    }

    @Override
    public void showErrorMessage() {
        Snackbar.make(coordinatorLayout, getString(R.string.error_has_ocurred),
                Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void cancelRefreshDialog() {
        refreshLayout.setRefreshing(false);
    }

}
