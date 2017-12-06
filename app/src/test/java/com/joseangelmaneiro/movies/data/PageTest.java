package com.joseangelmaneiro.movies.data;

import com.joseangelmaneiro.movies.utils.TestUtils;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;


public class PageTest {

    @Test
    public void compareTwoIdenticalPagesReturnsEquals() throws Exception {
        Page pageOne = TestUtils.createMainPage();
        Page pageTwo = TestUtils.createMainPage();

        assertEquals(pageOne, pageTwo);
    }

    @Test
    public void compareTwoDifferentPagesReturnsNotEquals() throws Exception {
        Page pageOne = TestUtils.createMainPage();
        Page pageTwo = TestUtils.createAltPage();

        assertNotEquals(pageOne, pageTwo);
    }

}