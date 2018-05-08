package com.joseangelmaneiro.movies.platform.views;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import com.joseangelmaneiro.movies.EspressoDaggerMockRule;
import com.joseangelmaneiro.movies.R;
import com.joseangelmaneiro.movies.TestUtils;
import com.joseangelmaneiro.movies.domain.Movie;
import com.joseangelmaneiro.movies.domain.MoviesRepository;
import com.joseangelmaneiro.movies.matchers.RecyclerViewItemsCountMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.when;


public class MovieListActivityTest {

    @Rule
    public EspressoDaggerMockRule rule = new EspressoDaggerMockRule();

    @Rule
    public IntentsTestRule<MovieListActivity> activityRule = new IntentsTestRule<>(
            MovieListActivity.class, false, false);

    @Mock
    MoviesRepository repository;


    @Test
    public void showsTheExpectedNumberOfItems() throws Exception{
        List<Movie> movieList = givenAListOfMovies(false);

        startActivity();

        thenTheListShowsTheExpectedNumberOfItems(movieList.size());
    }

    @Test
    public void showsTheExpectedNumberOfItemsAfterSwipeRefreshLayout() throws Exception{
        // First we open the screen with an empty list
        givenAEmptyListOfMovies(false);

        startActivity();

        thenTheListShowsTheExpectedNumberOfItems(0);

        // Then we return to a list when the user refreshes data
        List<Movie> movieList = givenAListOfMovies(true);

        onView(withId(R.id.refreshLayout)).perform(swipeDown());

        thenTheListShowsTheExpectedNumberOfItems(movieList.size());
    }

    @Test
    public void showsTheExpectedErrorMessage() throws Exception {
        Exception exception = givenAException();

        startActivity();

        onView(withText(exception.getMessage())).check(matches(isDisplayed()));
    }

    @Test
    public void opensDetailScreenWhenAMovieIsSelected() throws Exception {
        List<Movie> movieList = givenAListOfMovies(false);
        int movieIndex = 0;
        startActivity();

        onView(withId(R.id.recyclerView)).
                perform(RecyclerViewActions.actionOnItemAtPosition(movieIndex, click()));

        Movie movieSelected = movieList.get(movieIndex);
        intended(hasComponent(DetailMovieActivity.class.getCanonicalName()));
        intended(hasExtra(DetailMovieActivity.EXTRA_MOVIE_ID, movieSelected.getId()));
    }


    private List<Movie> givenAListOfMovies(boolean refresh) throws Exception {
        List<Movie> movieList = new ArrayList<>();
        for(int i=0; i<5; i++){
            int movieId = i;
            Movie movie = TestUtils.getMovie(movieId);
            movieList.add(movie);
            when(repository.getMovie(movieId)).thenReturn(movie);
        }
        when(repository.getMovies(refresh)).thenReturn(movieList);
        return movieList;
    }

    private Exception givenAException() throws Exception{
        Exception exception = new Exception("Fake Exception");
        when(repository.getMovies(anyBoolean())).thenThrow(exception);
        return exception;
    }

    private void givenAEmptyListOfMovies(boolean refresh) throws Exception{
        when(repository.getMovies(refresh)).thenReturn(Collections.emptyList());
    }

    private void thenTheListShowsTheExpectedNumberOfItems(int numberOfItems){
        onView(withId(R.id.recyclerView))
                .check(matches(RecyclerViewItemsCountMatcher.recyclerViewHasItemCount(numberOfItems)));
    }

    private MovieListActivity startActivity() {
        return activityRule.launchActivity(null);
    }

}