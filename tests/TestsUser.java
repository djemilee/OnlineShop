package tests;

import org.junit.Before;
import org.junit.Test;
import shop.Vmzona;

import static junit.framework.TestCase.*;

public class TestsUser {

    Vmzona shop;

    @Before
    public void setUp(){
        shop = new Vmzona("Djemi N1");
    }

    @Test
    public void testCreateNewUser(){

    }
}
