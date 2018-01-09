package com.joseangelmaneiro.movies.ui.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.joseangelmaneiro.movies.R;
import com.joseangelmaneiro.movies.di.Injection;
import com.joseangelmaneiro.movies.ui.Formatter;
import com.squareup.picasso.Picasso;
import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailMovieActivity extends AppCompatActivity implements DetailMovieView {

    public static final String EXTRA_MOVIE_ID = "MOVIE_ID";

    private DetailMoviePresenter presenter;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.image_movie) ImageView movieImageView;
    @BindView(R.id.text_voteAverage) TextView voteAverageTextView;
    @BindView(R.id.text_releaseDate) TextView releaseDateTextView;
    @BindView(R.id.text_overview) TextView overviewTextView;


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

}
