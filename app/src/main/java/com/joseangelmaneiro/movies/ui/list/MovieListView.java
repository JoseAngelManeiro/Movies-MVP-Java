package com.joseangelmaneiro.movies.ui.list;

import com.joseangelmaneiro.movies.ui.BaseView;
import com.joseangelmaneiro.movies.ui.MovieViewModel;
import java.util.List;


public interface MovieListView extends BaseView{

    void refreshList(List<MovieViewModel> items);

    void cancelRefreshDialog();

    void navigateToDetailScreen(int id);

}
