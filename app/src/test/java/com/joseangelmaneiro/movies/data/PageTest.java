package com.joseangelmaneiro.movies.data;

import com.joseangelmaneiro.movies.data.entity.PageEntity;
import com.joseangelmaneiro.movies.utils.TestUtils;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;


public class PageTest {

    @Test
    public void compareTwoIdenticalPagesReturnsEquals() throws Exception {
        PageEntity pageOne = TestUtils.createMainPage();
        PageEntity pageTwo = TestUtils.createMainPage();

        assertEquals(pageOne, pageTwo);
    }

    @Test
    public void compareTwoDifferentPagesReturnsNotEquals() throws Exception {
        PageEntity pageOne = TestUtils.createMainPage();
        PageEntity pageTwo = TestUtils.createAltPage();

        assertNotEquals(pageOne, pageTwo);
    }

}