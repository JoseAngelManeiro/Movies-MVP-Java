package com.joseangelmaneiro.movies.data.source.remote;

import com.joseangelmaneiro.movies.data.entity.MovieEntity;
import com.joseangelmaneiro.movies.data.exception.ServiceException;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class MoviesRemoteDataSourceImplTest extends MockWebServerTest {

    MoviesRemoteDataSourceImpl sut;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        String mockWebServerEndpoint = getBaseEndpoint();
        MovieService movieService = new Retrofit.Builder()
                .baseUrl(mockWebServerEndpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService.class);
        sut = new MoviesRemoteDataSourceImpl(movieService);
    }

    @Test
    public void sendsGetAllRequestToTheCorrectEndpoint() throws Exception {
        enqueueMockResponse();

        sut.getAll();

        assertGetRequestSentTo("/movie/popular?api_key=");
    }

    @Test
    public void parseDataProperlyWhenReceivingAMovieList() throws Exception {
        enqueueMockResponse(200, "getAllResponse.json");

        List<MovieEntity> movieEntityList = sut.getAll();

        assertThat(movieEntityList.size(), is(20));
        assertTaskContainsExpectedValues(movieEntityList.get(0));
    }

    @Test(expected = ServiceException.class)
    public void firesServiceExceptionWhenResponseIsNotSuccesful() throws Exception {
        enqueueMockResponse(401);

        sut.getAll();
    }

    private void assertTaskContainsExpectedValues(MovieEntity movieEntity) {
        assertEquals(movieEntity.voteCount, 1282);
        assertEquals(movieEntity.id, 383498);
        assertFalse(movieEntity.video);
        assertEquals(movieEntity.voteAverage, "7.9");
        assertEquals(movieEntity.title, "Deadpool 2");
        assertThat(movieEntity.popularity, is(Float.parseFloat("497.250634")));
        assertEquals(movieEntity.posterPath, "/to0spRl1CMDvyUbOnbb4fTk3VAd.jpg");
        assertEquals(movieEntity.originalLanguage, "en");
        assertEquals(movieEntity.originalTitle, "Deadpool 2");
    }


}