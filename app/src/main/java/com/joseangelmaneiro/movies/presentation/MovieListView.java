package com.joseangelmaneiro.movies.presentation;


public interface MovieListView extends BaseView{

    void refreshList();

    void cancelRefreshDialog();

    void navigateToDetailScreen(int movieId);

}
