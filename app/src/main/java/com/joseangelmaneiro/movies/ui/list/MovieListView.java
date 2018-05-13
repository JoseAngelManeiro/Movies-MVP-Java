package com.joseangelmaneiro.movies.ui.list;

import com.joseangelmaneiro.movies.ui.BaseView;


public interface MovieListView extends BaseView{

    void refreshList();

    void cancelRefreshDialog();

    void navigateToDetailScreen(int movieId);

}
