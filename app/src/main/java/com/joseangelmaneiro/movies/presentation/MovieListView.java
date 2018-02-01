package com.joseangelmaneiro.movies.presentation;

import com.joseangelmaneiro.movies.ui.BaseView;


public interface MovieListView extends BaseView{

    void refreshList();

    void cancelRefreshDialog();

    void navigateToDetailScreen(int movieId);

}
