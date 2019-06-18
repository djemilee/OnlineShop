package tests;

import org.junit.Before;
import org.junit.Test;
import shop.Stock;

import static junit.framework.TestCase.*;

public class TestsStocks {

    Stock stock, stock1, stock2;

    @Before
    public void setUp(){
        stock = new Stock("silikonov chasovnik", 35, "adidas", "black");
        stock1 = new Stock("grivna", 28, "zara", "silver");
        stock2 = new Stock("roklq", 40, "nike", "red");
    }

    @Test
    public void testGiveStock(){

    }


}
