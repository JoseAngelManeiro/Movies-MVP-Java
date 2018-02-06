package com.joseangelmaneiro.movies.platform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.joseangelmaneiro.movies.R;
import com.joseangelmaneiro.movies.di.Injection;
import com.joseangelmaneiro.movies.presentation.presenters.DetailMoviePresenter;
import com.joseangelmaneiro.movies.presentation.DetailMovieView;
import com.joseangelmaneiro.movies.presentation.formatters.Formatter;
import com.squareup.picasso.Picasso;
import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailMovieActivity extends BaseActivity implements DetailMovieView {

    public static final String EXTRA_MOVIE_ID = "MOVIE_ID";

    private DetailMoviePresenter presenter;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.image_movie) ImageView movieImageView;
    @BindView(R.id.text_voteAverage) TextView voteAverageTextView;
    @BindView(R.id.text_releaseDate) TextView releaseDateTextView;
    @BindView(R.id.text_overview) TextView overviewTextView;

    public static void launch(Activity activity, int movieId) {
        Intent intent = new Intent(activity, DetailMovieActivity.class);
        intent.putExtra(DetailMovieActivity.EXTRA_MOVIE_ID, movieId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        bindViews();

        setUpPresenter();

        setUpActionBar();

        informPresenterViewIsReady();

    }

    private void bindViews(){
        ButterKnife.bind(this);
    }

    private void setUpPresenter(){
        int movieId = getIntent().getIntExtra(EXTRA_MOVIE_ID, -1);
        presenter = new DetailMoviePresenter(
                Injection.provideRepository(getApplicationContext()),
                new Formatter(),
                movieId);
        presenter.setView(this);
    }

    private void setUpActionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void informPresenterViewIsReady(){
        presenter.viewReady();
    }

    @Override
    public void displayImage(String url) {
        Picasso.with(this)
                .load(url)
                .into(movieImageView);
    }

    @Override
    public void displayTitle(String title) {
        setTitle(title);
    }

    @Override
    public void displayVoteAverage(String voteAverage) {
        voteAverageTextView.setText(voteAverage);
    }

    @Override
    public void displayReleaseDate(String releaseDate) {
        releaseDateTextView.setText(releaseDate);
    }

    @Override
    public void displayOverview(String overview) {
        overviewTextView.setText(overview);
    }

    @Override
    public void goToBack() {
        onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==android.R.id.home){
            presenter.navUpSelected();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
