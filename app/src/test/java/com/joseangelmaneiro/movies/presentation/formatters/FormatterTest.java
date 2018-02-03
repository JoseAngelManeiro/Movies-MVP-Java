package com.joseangelmaneiro.movies.presentation.formatters;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class FormatterTest {

    Formatter sut;

    @Before
    public void setUp() throws Exception {
        sut = new Formatter();
    }

    @After
    public void tearDown() throws Exception {
        sut = null;
    }

    @Test
    public void getCompleteUrlImage_ReturnsCorrectUrl(){
        String fakePath = "fake-path";
        String urlExpected = Formatter.BASE_URL_IMAGE + fakePath;

        assertEquals(urlExpected, sut.getCompleteUrlImage(fakePath));
    }

    @Test
    public void formatDate_ReturnsCorrectDate(){
        String serverDate = "2017-10-22";
        String dateExpected = "22/10/2017";

        assertEquals(dateExpected, sut.formatDate(serverDate));
    }

}