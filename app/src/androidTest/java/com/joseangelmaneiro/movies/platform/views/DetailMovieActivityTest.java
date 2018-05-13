package com.joseangelmaneiro.movies.platform.views;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import com.joseangelmaneiro.movies.CustomScrollActions;
import com.joseangelmaneiro.movies.EspressoDaggerMockRule;
import com.joseangelmaneiro.movies.R;
import com.joseangelmaneiro.movies.TestUtils;
import com.joseangelmaneiro.movies.domain.Movie;
import com.joseangelmaneiro.movies.domain.MoviesRepository;
import com.joseangelmaneiro.movies.presentation.formatters.Formatter;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.joseangelmaneiro.movies.matchers.ToolbarMatcher.onToolbarWithTitle;
import static org.hamcrest.Matchers.allOf;
import static org.mockito.Mockito.when;


public class DetailMovieActivityTest {

    private static final int MOVIE_ID = 1234;

    @Rule
    public EspressoDaggerMockRule rule = new EspressoDaggerMockRule();

    @Rule
    public ActivityTestRule<DetailMovieActivity> activityRule = new ActivityTestRule<>(
            DetailMovieActivity.class, false, false);

    @Mock
    MoviesRepository repository;

    @Test
    public void showsMovieTitleAsToolbarTitle() {
        Movie movie = givenAMovie();

        startActivity(movie);

        onToolbarWithTitle(movie.getTitle()).check(matches(isDisplayed()));
    }

    @Test
    public void showsMovieVoteAverage() {
        Movie movie = givenAMovie();

        startActivity(movie);
        scrollToView(R.id.text_voteAverage);

        onView(allOf(withId(R.id.text_voteAverage), withText(movie.getVoteAverage())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void showsMovieReleaseDate() {
        Movie movie = givenAMovie();
        String releaseDate = new Formatter().formatDate(movie.getReleaseDate());

        startActivity(movie);
        scrollToView(R.id.text_releaseDate);

        onView(allOf(withId(R.id.text_releaseDate), withText(releaseDate)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void showsMovieOverview() {
        Movie movie = givenAMovie();

        startActivity(movie);
        scrollToView(R.id.text_overview);

        onView(allOf(withId(R.id.text_overview), withText(movie.getOverview())))
                .check(matches(isDisplayed()));
    }

    private Movie givenAMovie(){
        Movie movie = TestUtils.getMovie(MOVIE_ID);
        when(repository.getMovie(MOVIE_ID)).thenReturn(movie);
        return movie;
    }

    private DetailMovieActivity startActivity(Movie movie) {
        Intent intent = new Intent();
        intent.putExtra(DetailMovieActivity.EXTRA_MOVIE_ID, movie.getId());
        return activityRule.launchActivity(intent);
    }

    private void scrollToView(int viewId) {
        onView(withId(viewId)).perform(CustomScrollActions.nestedScrollTo());
    }

}